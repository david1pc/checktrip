package com.proyecto.checktrip.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Entity(name = "ClienteIdaVueltaViajes")
public class ClienteIdaVueltaViajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne
    private Client client;

    @OneToOne
    private Viaje viajeIda;

    @OneToOne
    private Viaje viajeVuelta;

    private LocalDateTime fechaCreacion;
}
