package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.ClienteViajeIdaRequestDTO;
import com.proyecto.checktrip.dto.ClienteViajeIdaResponseDTO;
import com.proyecto.checktrip.dto.ClienteViajeIdaVueltaRequestDTO;
import com.proyecto.checktrip.dto.ClienteViajeIdaVueltaResponseDTO;
import com.proyecto.checktrip.entities.Itinerary;
import org.springframework.stereotype.Service;
@Service
public interface ClienteViajeService {

    public ClienteViajeIdaResponseDTO guardarItinerarioIda (ClienteViajeIdaRequestDTO clienteViajeIdaRequestDTO);
    public ClienteViajeIdaVueltaResponseDTO guardarItinerarioIdaVuelta (ClienteViajeIdaVueltaRequestDTO clienteViajeIdaVueltaRequestDTO);

    public String eliminarItinerarioIda(String username, Integer clienteIdaViajeCodigo);
    public String eliminarItinerarioIdaVuelta(String username, Integer clienteIdaVueltaViajeCodigo);

}
