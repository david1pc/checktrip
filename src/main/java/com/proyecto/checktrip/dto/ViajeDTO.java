package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ViajeDTO(Integer numberOfBookableSeats,
                       PriceDTO price,
                       ItineraryDTO itineraryDTO,
                       DictionariesDTO dictionaries) {
}
