package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClienteViajeIdaVueltaResponseDTO(String username,
                                               ViajeDTO viajeIda,
                                               ViajeDTO viajeVuelta,
                                               LocalDateTime fechaCreacion) {
}
