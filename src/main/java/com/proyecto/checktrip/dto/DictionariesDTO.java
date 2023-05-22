package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DictionariesDTO(List<AircraftDTO> aircraft,
                              List<CarriersDTO> carriers) {
}
