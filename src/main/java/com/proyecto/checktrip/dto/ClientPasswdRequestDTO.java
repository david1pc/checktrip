package com.proyecto.checktrip.dto;

import lombok.Builder;

@Builder
public record ClientPasswdRequestDTO(String username,
                                     String password,
                                     String newPassword) {
}
