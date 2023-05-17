package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ViajeDTO(Integer numberOfBookableSeats,
                       PriceDTO price,
                       List<ItineraryDTO> itineraries,
                       List<DictionariesDTO> dictionaries) {
}
