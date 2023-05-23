package com.proyecto.checktrip.dto;

import lombok.Builder;

@Builder
public record ViajeDTO(Integer numberOfBookableSeats,
                       PriceDTO price,
                       ItineraryDTO itineraryDTO,
                       DictionariesDTO dictionaries) {
}
