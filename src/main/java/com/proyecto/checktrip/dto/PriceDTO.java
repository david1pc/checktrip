package com.proyecto.checktrip.dto;

import lombok.Builder;

@Builder
public record PriceDTO(String currency,
                       Double total,
                       Double base,
                       Double grandTotal) {
}
