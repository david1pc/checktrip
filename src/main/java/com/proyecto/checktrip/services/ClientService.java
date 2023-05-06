package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.ClientRequestDTO;
import com.proyecto.checktrip.dto.ClientResponseDTO;
import com.proyecto.checktrip.entities.Client;
import com.proyecto.checktrip.entities.Person;

public interface ClientService {
    public ClientResponseDTO createClient(ClientRequestDTO client)throws Exception;
    public Person findClientByUsername(String username)throws Exception;
}
