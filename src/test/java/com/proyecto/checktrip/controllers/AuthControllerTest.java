package com.proyecto.checktrip.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.repo.RoleClientRepo;
import com.proyecto.checktrip.repo.RoleRepo;
import com.proyecto.checktrip.services.ClienteViajeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AuthController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
class AuthControllerTest {
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AuthController authController;
    @MockBean
    private RoleRepo roleRepo;
    @MockBean
    private RoleClientRepo roleClientRepo;
    @MockBean
    private JwtEncoder jwtEncoder;
    @MockBean
    private JwtDecoder jwtDecoder;
    @MockBean
    private TokenService tokenService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void login() throws Exception {
        LoginDTO loginDTO = LoginDTO.builder()
                .username("davidpc")
                .password("12345")
                .build();
        String json = this.mapper.writeValueAsString(loginDTO);
        this.realizarPeticion("/api/auth/login", 200, json, "post");
    }

    @Test
    void loginError() throws Exception {
        String valor = "username";
        String json = this.mapper.writeValueAsString(valor);
        this.realizarPeticion("/api/auth/login", 400, json, "post");
    }

    @Test
    void createClient() throws Exception {
        ClientRequestDTO clientRequestDTO = this.retornarCliente();
        String json = this.mapper.writeValueAsString(clientRequestDTO);
        this.realizarPeticion("/api/auth/register", 200, json, "post");
    }

    private void realizarPeticion(String url, Integer status, String json, String tipoPeticion) throws Exception {
        RequestBuilder request = null;
        if(tipoPeticion.equalsIgnoreCase("post")){
            request = MockMvcRequestBuilders
                    .post(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON);
        }

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is(status))
                .andReturn();
    }

    private ClientRequestDTO retornarCliente(){
        PersonDTO personDTO = PersonDTO.builder()
                .username("prueba1")
                .password("12345")
                .correo("prueba1@gmail.com")
                .nombres("prueba")
                .apellidos("aa")
                .build();
        return ClientRequestDTO.builder()
                .person(personDTO)
                .build();
    }

    @Test
    void updatePasswdClient() throws Exception {
        ClientPasswdRequestDTO clientPasswdRequestDTO = ClientPasswdRequestDTO.builder()
                .newPassword("123")
                .password("11581")
                .username("davidpc")
                .build();
        String json = this.mapper.writeValueAsString(clientPasswdRequestDTO);
        this.realizarPeticion("/api/auth/update-passwd", 200, json, "post");
    }

    @Test
    void updatePasswdClientWithError() throws Exception {
        String valor = "username";
        String json = this.mapper.writeValueAsString(valor);
        this.realizarPeticion("/api/auth/update-passwd", 400, json, "post");
    }

    @Test
    void recoverAccount() throws Exception {
        AccountRecoveryRequestDTO accountRecoveryRequestDTO = AccountRecoveryRequestDTO.builder()
                .correo("david1pc4@gmail.com")
                .build();
        String json = this.mapper.writeValueAsString(accountRecoveryRequestDTO);
        this.realizarPeticion("/api/auth/recover-account", 200, json, "post");
    }
}