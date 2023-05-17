package com.proyecto.checktrip.dto;

import lombok.Builder;

@Builder
public record AircraftDTO(String id,
                          String name) {
}
