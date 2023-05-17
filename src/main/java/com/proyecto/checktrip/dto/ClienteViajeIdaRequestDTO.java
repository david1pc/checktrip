package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClienteViajeIdaRequestDTO(String username,
                                        ViajeDTO viaje,
                                        LocalDateTime fechaCreacion) {
}
