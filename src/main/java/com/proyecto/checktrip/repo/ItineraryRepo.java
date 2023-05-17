package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepo extends JpaRepository<Itinerary,Integer> {
}
