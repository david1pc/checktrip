package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person,Integer> {
    Optional<Person> findFirstByCorreoOrUsername(String correo, String username);
    Optional<Person> findByUsername(String username);
    Optional<Person> findByCorreo(String correo);
}
