package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.entities.HistorialVuelo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface HistorialService {

    public void guardarHistorial (HistorialDTO historial);
    public List<HistorialVuelo> filter(LocalDate fechaInicio, LocalDate  fechaFin, String username);

}
