package com.proyecto.checktrip.dto;

import com.proyecto.checktrip.entities.Segment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SegmentMapperDTO implements Function<Segment, SegmentDTO> {
    @Override
    public SegmentDTO apply(Segment segment) {
        return SegmentDTO.builder()
                .departure(ArrivalDTO.builder()
                        .terminal(segment.getDeparture().getTerminal())
                        .iataCode(segment.getDeparture().getIataCode())
                        .at(segment.getDeparture().getAt())
                        .build())
                .arrival(ArrivalDTO.builder()
                        .at(segment.getArrival().getAt())
                        .iataCode(segment.getArrival().getIataCode())
                        .terminal(segment.getArrival().getTerminal())
                        .build())
                .id(segment.getId())
                .duration(segment.getDuration())
                .number(segment.getNumber())
                .aircraft(SegmentAircraftDTO.builder()
                        .code(segment.getAircraft().getCode())
                        .build())
                .numberOfStops(segment.getNumberOfStops())
                .blacklistedInEU(segment.getBlackListedInEU())
                .carrierCode(segment.getCarrierCode())
                .build();
    }
}
