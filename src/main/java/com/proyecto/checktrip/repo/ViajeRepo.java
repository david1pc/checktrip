package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepo extends JpaRepository<Viaje,Integer> {
}
