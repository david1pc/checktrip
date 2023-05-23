package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.exceptions.ClientePasswordNoCoincide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ClientServiceImplTest {
    @Autowired
    private ClientService clientService;
    @MockBean
    private JwtEncoder jwtEncoder;
    @MockBean
    private JwtDecoder jwtDecoder;
    @MockBean
    private TokenService tokenService;

    @Test
    void createClient() {
        ClientRequestDTO clientRequestDTO = this.retornarCliente();
        ClientResponseDTO clientResponseDTO = this.clientService.createClient(clientRequestDTO);
        Assertions.assertEquals(clientRequestDTO.person().nombres(), clientResponseDTO.nombres());
    }

    private ClientRequestDTO retornarCliente(){
        PersonDTO personDTO = PersonDTO.builder()
                .username("prueba521")
                .password("12345")
                .correo("prueba10@gmail.com")
                .nombres("prueba")
                .apellidos("aa")
                .build();
        return ClientRequestDTO.builder()
                .person(personDTO)
                .build();
    }

    @Test
    void recoverAccount() {
        String expected = "Si el correo ingresado corresponde a una cuenta registrada en CheckTrip, le solicitamos que verifique su correo ya que se le ha enviado una contraseña temporal";
        AccountRecoveryRequestDTO accountRecoveryRequestDTO = AccountRecoveryRequestDTO.builder()
                .correo("david1pc4afefsd@gmail.com")
                .build();
        AccountRecoveryResponseDTO accountRecoveryResponseDTO = this.clientService.recoverAccount(accountRecoveryRequestDTO);
        Assertions.assertEquals(expected, accountRecoveryResponseDTO.descripcion());
    }

    @Test
    void verifyTemporalPasswd() {
        Boolean expected = false;
        LoginDTO loginDTO = LoginDTO.builder()
                .username("davidpc")
                .password("12345")
                .build();

        Boolean respuesta = this.clientService.verifyTemporalPasswd(loginDTO);
        Assertions.assertEquals(expected, respuesta);
    }

    @Test
    void updateAccount() {
        String expected = "Se ha actualizado la password con exito.";
        ClientPasswdRequestDTO clientPasswdRequestDTO = ClientPasswdRequestDTO.builder()
                .username("davidpc")
                .newPassword("12345")
                .password("12345")
                .build();
        ClientPasswdResponseDTO clientPasswdResponseDTO = this.clientService.updateAccount(clientPasswdRequestDTO);
        Assertions.assertEquals(expected, clientPasswdResponseDTO.descripcion());
    }

    @Test
    void updateAccountError() {
        String expected = "El username o contraseña es incorrecto";
        ClientPasswdRequestDTO clientPasswdRequestDTO = ClientPasswdRequestDTO.builder()
                .username("davidpc")
                .newPassword("1234sdwasd5")
                .password("123dwadsdawd45")
                .build();
        ClientePasswordNoCoincide exception = Assertions.assertThrows(ClientePasswordNoCoincide.class, () -> this.clientService.updateAccount(clientPasswdRequestDTO));
        Assertions.assertEquals(expected, exception.getMessage());
    }
}