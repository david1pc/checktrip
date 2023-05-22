package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClienteViajeIdaVueltaRequestDTO(String username,
                                              ViajeDTO viajeIda,
                                              ViajeDTO viajeVuelta,
                                              LocalDateTime fechaCreacion) {
}
