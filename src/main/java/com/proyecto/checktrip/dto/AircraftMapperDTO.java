package com.proyecto.checktrip.dto;

import com.proyecto.checktrip.entities.Aircraft;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AircraftMapperDTO implements Function<Aircraft, AircraftDTO> {

    @Override
    public AircraftDTO apply(Aircraft aircraft) {
        return AircraftDTO.builder()
                .name(aircraft.getName())
                .id(aircraft.getId())
                .build();
    }
}
