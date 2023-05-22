package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PersonRepoTest {

    @Autowired
    private PersonRepo personRepo;

    @Test
    public void deberiaCrearPersona(){
        Person person = Person.builder()
                .username("prueba2")
                .estado(true)
                .password("12345")
                .correo("prueba2@gmail.com")
                .nombres("prueba")
                .apellidos("aa")
                .build();
        Person newPerson = personRepo.save(person);

        Person expected = personRepo.findByUsername(person.getUsername()).get();

        Assertions.assertThat(expected).isEqualTo(newPerson);
    }
}
