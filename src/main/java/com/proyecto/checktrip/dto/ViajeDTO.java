package com.proyecto.checktrip.dto;

import lombok.Builder;

@Builder
public record ViajeDTO(Integer numberOfBookableSeats,
                       PriceDTO price,
                       String travelClass,
                       ItineraryDTO itineraryDTO,
                       DictionariesDTO dictionaries) {
}
