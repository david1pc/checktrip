package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;

public interface ClienteViajeService {

    public ItineraryClientDTO guardarItinerario (String username, ViajeDTO viajeDTO);
    public String guardarItinerarioIda(ClienteViajeIdaRequestDTO clienteViajeIdaRequestDTO);
    public String guardarItinerarioIdaVuelta(ClienteViajeIdaVueltaRequestDTO clienteViajeIdaVueltaRequestDTO);
    public ItinerariesClientDTO obtenerViajes(String username);
}
