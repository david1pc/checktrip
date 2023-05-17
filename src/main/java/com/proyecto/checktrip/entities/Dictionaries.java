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
@Entity(name = "Dictionaries")
public class Dictionaries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne
    private Viaje viaje;

    @ToString.Exclude
    @OneToMany(mappedBy = "dictionaries")
    private List<DictionaryCarriers> carriers;

    @ToString.Exclude
    @OneToMany(mappedBy = "dictionaries")
    private List<DictionaryAircraft> aircrafts;
}
