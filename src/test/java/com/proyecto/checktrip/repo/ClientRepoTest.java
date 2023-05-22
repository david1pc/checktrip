package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Client;
import com.proyecto.checktrip.entities.Person;
import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.entities.RoleClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class ClientRepoTest {
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private RoleClientRepo roleClientRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Test
    public void deberiaCrearCliente(){
        Person person = Person.builder()
                .username("prueba1")
                .estado(true)
                .password("12345")
                .correo("prueba1@gmail.com")
                .nombres("prueba")
                .apellidos("aa")
                .build();

        Person person1 = this.personRepo.save(person);

        Role role = new Role("CLIENT");

        Role role1 = this.roleRepo.save(role);

        Client client = Client.builder()
                .persona(person1)
                .build();

        Client client1 = this.clientRepo.save(client);

        RoleClient roleClient = new RoleClient(role1, client1);

        this.roleClientRepo.save(roleClient);

        Client expected = this.clientRepo.findById(client1.getCodigo()).get();

        Assertions.assertThat(expected).isEqualTo(client1);
    }


}