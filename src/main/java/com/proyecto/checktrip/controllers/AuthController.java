package com.proyecto.checktrip.controllers;

import com.proyecto.checktrip.dto.ClientRequestDTO;
import com.proyecto.checktrip.dto.ClientResponseDTO;
import com.proyecto.checktrip.dto.LoginDTO;
import com.proyecto.checktrip.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClientServiceImpl clientService;
    private final PersonServiceImpl personService;


    @PostMapping("/login")
    public String login(@RequestBody LoginDTO login) {
        String token = personService.loguearse(login);
        return token;
    }

    @PostMapping("/register")
    public ClientResponseDTO createClient(@RequestBody ClientRequestDTO client) {
        ClientResponseDTO newClient = this.clientService.createClient(client);
        return newClient;
    }


    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @GetMapping("/index")
    public String index(Principal principal){
        return "Hola " + principal.getName();
    }
}
