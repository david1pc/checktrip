package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.entities.Client;
import com.proyecto.checktrip.entities.Person;

public interface ClientService {
    public ClientResponseDTO createClient(ClientRequestDTO client)throws Exception;
    public AccountRecoveryResponseDTO recoverAccount(AccountRecoveryRequestDTO accountRecoveryRequestDTO);
    public Boolean verifyTemporalPasswd(LoginDTO loginDTO);
    public ClientPasswdResponseDTO updateAccount(ClientPasswdRequestDTO client);
    public Person findClientByUsername(String username)throws Exception;
}
