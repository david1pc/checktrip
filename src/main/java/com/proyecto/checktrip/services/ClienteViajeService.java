package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;

public interface ClienteViajeService {

    public ItineraryClientDTO guardarItinerario (String username, ViajeDTO viajeDTO);
    public ClienteViajeIdaResponseDTO guardarItinerarioIda(ClienteViajeIdaRequestDTO clienteViajeIdaRequestDTO);
    public ClienteViajeIdaVueltaResponseDTO guardarItinerarioIdaVuelta(ClienteViajeIdaVueltaRequestDTO clienteViajeIdaVueltaRequestDTO);
    public ItinerariesClientDTO obtenerViajes(String username);
}
