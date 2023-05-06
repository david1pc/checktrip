package com.proyecto.checktrip.dto;

import lombok.Builder;

@Builder
public record ClientResponseDTO(Integer codigo,
                                String nombres,
                                String apellidos) {
}
