package com.proyecto.checktrip.services;

import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.entities.*;
import com.proyecto.checktrip.exceptions.ClientePasswordNoCoincide;
import com.proyecto.checktrip.exceptions.PersonaNoExiste;
import com.proyecto.checktrip.exceptions.PersonaYaExiste;
import com.proyecto.checktrip.repo.ClientRepo;
import com.proyecto.checktrip.repo.HistorialRepo;
import com.proyecto.checktrip.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class HistorialServiceImpl implements HistorialService{
    private final HistorialRepo historialRepo;

    @Override
    public void guardarHistorial(HistorialDTO historial) {

            HistorialVuelo historialVuelo = HistorialVuelo.builder()
                .fechaSalida(historial.fechaSalida())
                .fechaVuelta(historial.fechaVuelta())
                .soloIda(historial.soloIda())
                .clase(historial.clase())
                .destino(historial.destino())
                .origen(historial.origen())
                .objeto(historial.objeto())
                .build();

        historialRepo.save(historialVuelo);
    }

    @Override
    public List<HistorialVuelo> filter(LocalDate fechaInicio, LocalDate  fechaFin, String username) {

        List<HistorialVuelo> lista = null;
        if (fechaFin == null){
            lista=historialRepo.buscarPorFecha(fechaInicio);
        }
        else {
            lista = historialRepo.buscarPorFechas(fechaInicio, fechaFin);
        }
        return lista;
    }
}
