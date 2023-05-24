package com.proyecto.checktrip.services;

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
class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @MockBean
    private JwtEncoder jwtEncoder;
    @MockBean
    private JwtDecoder jwtDecoder;
    @MockBean
    private TokenService tokenService;

    @Test
    void enviarCorreo() {
        Boolean expected = true;
        Boolean response = emailService.enviarCorreo("Recuperar contraseña", "La nueva contraseña es: adDGT24", "pruebchecktripp@gmail.com");
        Assertions.assertEquals(expected, response);
    }
}