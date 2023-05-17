package com.proyecto.checktrip.dto;

import lombok.Builder;

@Builder
public record SegmentDTO(ArrivalDTO departure,
                         ArrivalDTO arrival,
                         String carrierCode,
                         String number,
                         SegmentAircraftDTO segmentAircraft,
                         OperatingDTO operating,
                         String duration,
                         String id,
                         Integer numberOfStops,
                         Boolean blacklistedInEU) {
}
