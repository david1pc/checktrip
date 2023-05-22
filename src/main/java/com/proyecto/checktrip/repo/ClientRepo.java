package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client,Integer> {

    @Query("SELECT c from Client c join Person p on p.codigo=:personCodigo where p.codigo=c.persona.codigo")
    Optional<Client> findClientByPersonId(Integer personCodigo);
}
