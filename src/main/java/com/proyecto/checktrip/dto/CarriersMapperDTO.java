package com.proyecto.checktrip.dto;
import com.proyecto.checktrip.entities.Carriers;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CarriersMapperDTO implements Function<Carriers, CarriersDTO> {
    @Override
    public CarriersDTO apply(Carriers carriers) {
        return CarriersDTO.builder()
                .name(carriers.getName())
                .id(carriers.getId())
                .build();
    }
}
