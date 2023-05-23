package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PersonRepoTest {
    @Autowired
    private PersonRepo personRepo;

    @MockBean
    private JwtEncoder jwtEncoder;
    @MockBean
    private JwtDecoder jwtDecoder;
    @MockBean
    private TokenService tokenService;

    @Test
    void deberiaBuscarPersona(){
        String username = "davidpc";
        Person expected = personRepo.findByUsername(username).get();
        Assertions.assertThat(username).isEqualTo(expected.getUsername());
    }
}
