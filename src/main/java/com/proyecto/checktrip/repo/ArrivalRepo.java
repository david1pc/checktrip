package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Arrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalRepo extends JpaRepository<Arrival,String> {
}
