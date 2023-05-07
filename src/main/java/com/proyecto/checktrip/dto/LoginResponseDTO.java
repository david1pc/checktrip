package com.proyecto.checktrip.dto;

import lombok.Builder;

@Builder
public record LoginResponseDTO(String username, String token) {
}
