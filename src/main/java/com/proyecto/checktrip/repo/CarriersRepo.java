package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Carriers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarriersRepo extends JpaRepository<Carriers,Integer> {
    Optional<Carriers> findCarriersById(String id);
}
