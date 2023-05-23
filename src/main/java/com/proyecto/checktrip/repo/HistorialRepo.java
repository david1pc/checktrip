package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.HistorialVuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface HistorialRepo extends JpaRepository<HistorialVuelo,Integer> {

    @Query("SELECT e FROM HistorialVuelo e WHERE e.fechaSalida BETWEEN ?1 AND ?2 OR e.fechaVuelta BETWEEN ?1 AND ?2")
    public List<HistorialVuelo> buscarPorFechas(LocalDate fechaInicio, LocalDate  fechaFin);

    @Query("SELECT e FROM HistorialVuelo e WHERE e.fechaSalida = ?1 OR e.fechaVuelta = ?1")
    public List<HistorialVuelo> buscarPorFecha(LocalDate  fechaInicio);
}
