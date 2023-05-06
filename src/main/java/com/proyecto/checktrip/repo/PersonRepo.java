package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person,Integer> {
    Person findByUsername(String username);
}
