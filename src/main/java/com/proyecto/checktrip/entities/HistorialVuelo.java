package com.proyecto.checktrip.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class HistorialVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private LocalDate  fechaSalida;
    private LocalDate  fechaVuelta;
    private Boolean soloIda;
    private String origen;
    private String destino;
    private String clase;

    @Column(name="objeto", columnDefinition="json")
    private String objeto;





}
