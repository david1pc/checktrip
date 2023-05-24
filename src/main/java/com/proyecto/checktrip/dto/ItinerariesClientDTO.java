package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ItinerariesClientDTO(List<ClienteViajeIdaRequestDTO> viajesIda,
                                   List<ClienteViajeIdaVueltaRequestDTO> viajesVuelta) {
}
