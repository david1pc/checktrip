package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ItineraryDTO(String duration,
                           List<SegmentDTO> segments) {
}
