package com.proyecto.checktrip.services;

import com.proyecto.checktrip.entities.Client;
import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.entities.RoleClient;
import com.proyecto.checktrip.repo.ClientRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{
    private final ClientRepo clientRepo;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepo clientRepo, RoleServiceImpl roleService, PasswordEncoder passwordEncoder){
        this.clientRepo = clientRepo;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client createClient(Client client) throws Exception {
        // Antes de guardar verificar si ya existe una persona con el mismo correo o username
        client.setPassword(this.passwordEncoder.encode(client.getPassword()));
        Client client_save = this.clientRepo.save(client);
        Role role = this.roleService.findById(1).get();
        this.roleService.createRoleClient(new RoleClient(role, client_save));
        return this.clientRepo.findById(client_save.getCodigo()).get();
    }

    @Override
    public Client findClientByUsername(String username) throws Exception {
        return this.clientRepo.findByUsername(username);
    }
}
