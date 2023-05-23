package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.entities.Client;
import com.proyecto.checktrip.entities.Person;
import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.entities.RoleClient;
import com.proyecto.checktrip.exceptions.ClientePasswordNoCoincide;
import com.proyecto.checktrip.exceptions.PersonaNoExiste;
import com.proyecto.checktrip.exceptions.PersonaYaExiste;
import com.proyecto.checktrip.repo.ClientRepo;
import com.proyecto.checktrip.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepo clientRepo;
    private final RoleServiceImpl roleService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final PersonRepo personRepo;

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        personRepo.findFirstByCorreoOrUsername(clientRequestDTO.person().correo(), clientRequestDTO.person().username())
                .ifPresent(user -> {
                    throw new PersonaYaExiste("El username o correo ya esta siendo usado por alguien más");
                });
        Person person = Person.builder()
                .apellidos(clientRequestDTO.person().apellidos())
                .nombres(clientRequestDTO.person().nombres())
                .correo(clientRequestDTO.person().correo())
                .fechaNacimiento(clientRequestDTO.person().fecha_nacimiento())
                .password(this.passwordEncoder.encode(clientRequestDTO.person().password()))
                .username(clientRequestDTO.person().username())
                .passwordTemporal(false)
                .estado(true)
                .build();

        Person newPerson = personRepo.save(person);

        Client client = Client.builder()
                .persona(newPerson)
                .build();
        Client clientSave = this.clientRepo.save(client);
        this.roleService.findById(1).ifPresent(role1 -> {
            this.roleService.createRoleClient(new RoleClient(role1, clientSave));
        });
        return ClientResponseDTO.builder()
                .nombres(clientSave.getPersona().getNombres())
                .apellidos(clientSave.getPersona().getApellidos())
                .codigo(clientSave.getCodigo())
                .build();
    }

    @Override
    public AccountRecoveryResponseDTO recoverAccount(AccountRecoveryRequestDTO accountRecoveryRequestDTO) {
        personRepo.findByCorreo(accountRecoveryRequestDTO.correo())
                .ifPresent(user -> {
                    String newPasswd = generarCadenaAleatoria();
                    user.setPassword(this.passwordEncoder.encode(newPasswd));
                    user.setPasswordTemporal(true);
                    personRepo.save(user);
                    this.emailService.enviarCorreo("Recuperación de cuenta - CheckTrip", "La nueva password es la siguiente: " + newPasswd, user.getCorreo());
                });
        return AccountRecoveryResponseDTO.builder()
                .descripcion("Si el correo ingresado corresponde a una cuenta registrada en CheckTrip, le solicitamos que verifique su correo ya que se le ha enviado una contraseña temporal")
                .build();
    }

    @Override
    public Boolean verifyTemporalPasswd(LoginDTO loginDTO) {
        Person person = obtenerPersona(loginDTO.username());
        if(person == null || person.getPasswordTemporal() == null){
            return false;
        }else{
            return person.getPasswordTemporal();
        }
    }

    @Override
    public ClientPasswdResponseDTO updateAccount(ClientPasswdRequestDTO client) {
        Person person = obtenerPersona(client.username());
        if(this.passwordEncoder.matches(client.password(), person.getPassword())){
            person.setPasswordTemporal(false);
            person.setPassword(this.passwordEncoder.encode(client.newPassword()));
            personRepo.save(person);
            return ClientPasswdResponseDTO.builder()
                    .descripcion("Se ha actualizado la password con exito.")
                    .build();
        }else{
            throw new ClientePasswordNoCoincide("El username o contraseña es incorrecto");
        }
    }

    @Override
    public Person obtenerPersona(String username){
        return personRepo.findByUsername(username)
                .orElseThrow(() -> new PersonaNoExiste("El username o contraseña es incorrecto"));
    }

    private String generarCadenaAleatoria() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        int longitud = rnd.nextInt(6) + 5;
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int index = rnd.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }
}
