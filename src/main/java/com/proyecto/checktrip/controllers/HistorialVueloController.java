package com.proyecto.checktrip.controllers;

import com.proyecto.checktrip.dto.HistorialDTO;
import com.proyecto.checktrip.entities.HistorialVuelo;
import com.proyecto.checktrip.services.HistorialService;
import com.proyecto.checktrip.services.HistorialServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor

public class HistorialVueloController {


    private final HistorialServiceImpl historialService;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarHistorial(@RequestBody HistorialDTO historialDTO) {
        try {
            historialService.guardarHistorial(historialDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Información guardada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la información.");
        }
    }

    @GetMapping("/filter")
    public List<HistorialVuelo> filter(@RequestParam(name = "fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
                                       @RequestParam(name = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  fechaFin,
                                       @RequestParam(name = "username", required = false) String username) {

        List<HistorialVuelo> lista = historialService.filter(fechaInicio, fechaFin, username);
        return lista;
    }

    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @GetMapping("/vuelo")
    public String index(){
        return "Hola";
    }



}
