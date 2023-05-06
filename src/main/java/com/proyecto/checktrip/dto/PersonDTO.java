package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PersonDTO(String nombres,
                        String apellidos,
                        LocalDate fecha_nacimiento,
                        String correo,
                        String username,
                        String password
                        ) {
}
