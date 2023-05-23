package com.proyecto.checktrip.dto;

import com.proyecto.checktrip.entities.ClienteIdaViajes;
import com.proyecto.checktrip.entities.ClienteIdaVueltaViajes;
import lombok.Builder;

import java.util.List;

@Builder
public record ItinerariesClientDTO(List<ClienteIdaViajes> viajesIda,
                                   List<ClienteIdaVueltaViajes> viajesVuelta) {
}
