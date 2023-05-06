package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.ClientRequestDTO;
import com.proyecto.checktrip.entities.Client;
import com.proyecto.checktrip.entities.Person;
import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.entities.RoleClient;
import com.proyecto.checktrip.repo.ClientRepo;
import com.proyecto.checktrip.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepo clientRepo;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;
    private final PersonRepo personRepo;

    @Override
    public Client createClient(ClientRequestDTO clientRequestDTO) throws Exception {
        // Antes de guardar verificar si ya existe una persona con el mismo correo o username
        Person person = Person.builder()
                .apellidos(clientRequestDTO.person().apellidos())
                .nombres(clientRequestDTO.person().nombres())
                .correo(clientRequestDTO.person().correo())
                .fecha_nacimiento(clientRequestDTO.person().fecha_nacimiento())
                .password(this.passwordEncoder.encode(clientRequestDTO.person().password()))
                .username(clientRequestDTO.person().username())
                .build();

        Person newPerson = personRepo.save(person);

        Client client = Client.builder()
                .persona(newPerson)
                .build();
        Client client_save = this.clientRepo.save(client);
        Role role = this.roleService.findById(1).get();
        this.roleService.createRoleClient(new RoleClient(role, client_save));
        return this.clientRepo.findById(client_save.getCodigo()).get();
    }

    @Override
    public Person findClientByUsername(String username) throws Exception {
        return this.personRepo.findByUsername(username);
    }
}
