package com.proyecto.checktrip.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder
public record HistorialDTO(Integer id, LocalDate fechaSalida, LocalDate  fechaVuelta, Boolean soloIda,
                           String origen, String destino, String clase, String objeto ) {

}
