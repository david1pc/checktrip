package com.proyecto.checktrip.dto;

import com.proyecto.checktrip.entities.Client;
import com.proyecto.checktrip.entities.Viaje;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ItineraryClientDTO(Client client,
                                 Viaje viaje,
                                 LocalDateTime fechaCreacion) {
}
