package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.DictionaryAircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryAircraftRepo extends JpaRepository<DictionaryAircraft,Integer> {
}
