package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,Integer> {
    Client findByUsername(String username);
}
