package com.proyecto.checktrip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Entity(name = "Viaje")
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    private Integer numberOfBookeableSeats;
    @OneToOne
    private Price price;
    @ToString.Exclude
    @OneToMany(mappedBy = "viaje")
    List<Itinerary> itineraries;

    @ToString.Exclude
    @OneToMany(mappedBy = "viaje")
    List<Dictionaries> dictionaries;
}
