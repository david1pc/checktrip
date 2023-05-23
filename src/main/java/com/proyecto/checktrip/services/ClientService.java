package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.entities.Person;

public interface ClientService {
    public ClientResponseDTO createClient(ClientRequestDTO client);
    public AccountRecoveryResponseDTO recoverAccount(AccountRecoveryRequestDTO accountRecoveryRequestDTO);
    public Boolean verifyTemporalPasswd(LoginDTO loginDTO);
    public ClientPasswdResponseDTO updateAccount(ClientPasswdRequestDTO client);
    public Person obtenerPersona(String username);
}
