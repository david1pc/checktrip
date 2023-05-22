package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AircraftRepo extends JpaRepository<Aircraft,Integer> {

    Optional<Aircraft> findAircraftById(String id);
}
