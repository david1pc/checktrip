package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArrivalDTO(String iataCode,
                         String terminal,
                         LocalDateTime at) {
}
