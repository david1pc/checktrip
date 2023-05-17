package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.ClienteIdaVueltaViajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteIdaViajesRepo extends JpaRepository<ClienteIdaVueltaViajes,Integer> {
}
