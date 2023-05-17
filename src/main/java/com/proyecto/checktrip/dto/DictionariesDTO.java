package com.proyecto.checktrip.dto;

import java.util.List;

public record DictionariesDTO(List<AircraftDTO> aircraft,
                              List<CarriersDTO> carriers) {
}
