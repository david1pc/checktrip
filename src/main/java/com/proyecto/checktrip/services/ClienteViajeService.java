package com.proyecto.checktrip.services;

import com.proyecto.checktrip.entities.Itinerary;
import org.springframework.stereotype.Service;
@Service
public interface ClienteViajeService {

    public void guardarItinerario (Itinerary itinerario);

    public Itinerary eliminarItinerario (Itinerary itinerario);
}
