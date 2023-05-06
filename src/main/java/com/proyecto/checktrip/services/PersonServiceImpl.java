package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.LoginDTO;
import com.proyecto.checktrip.entities.Person;
import com.proyecto.checktrip.exceptions.PersonaInactiva;
import com.proyecto.checktrip.exceptions.PersonaNoExiste;
import com.proyecto.checktrip.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl {
    private final PersonRepo personRepo;


    public void verificarLogin(LoginDTO login){
        Boolean estado_persona = obtenerEstadoPersona(login.username());
        verificarEstadoPersona(login.username(), estado_persona);
    }

    private Boolean obtenerEstadoPersona(String username){
        Person persona = personRepo.findByUsername(username)
                .orElseThrow(() -> new PersonaNoExiste("No existe una persona con el username " + username));
        return persona.getEstado();
    }

    private void verificarEstadoPersona(String username, Boolean estado){
        if (!estado){
            throw new PersonaInactiva("La persona con username " + username + ", se encuentra inactivo");
        }
    }
}
