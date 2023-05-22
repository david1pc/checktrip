package com.proyecto.checktrip.services;

import com.proyecto.checktrip.entities.Itinerary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteViajeServiceImpl implements ClienteViajeService{

    @Override
    public void guardarItinerario(Itinerary itinerario) {

    }

    @Override
    public Itinerary eliminarItinerario(Itinerary itinerario) {
        return null;
    }
}
