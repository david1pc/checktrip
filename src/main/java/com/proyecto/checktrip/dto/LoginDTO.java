package com.proyecto.checktrip.dto;

import lombok.Builder;

@Builder
public record LoginDTO(String username, String password) {
}
