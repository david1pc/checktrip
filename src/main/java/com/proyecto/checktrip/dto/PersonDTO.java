package com.proyecto.checktrip.dto;

import java.time.LocalDate;

public record PersonDTO(String nombres,
                        String apellidos,
                        LocalDate fecha_nacimiento,
                        String correo,
                        String username,
                        String password
                        ) {
}
