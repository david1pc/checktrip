package com.proyecto.checktrip.services;

import com.proyecto.checktrip.entities.Client;

public interface ClientService {
    public Client createClient(Client client)throws Exception;
    public Client findClientByUsername(String username)throws Exception;
}
