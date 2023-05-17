package com.proyecto.checktrip.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Entity(name = "DictionaryAircraft")
public class DictionaryAircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @OneToOne
    private Aircraft aircraft;

    @ManyToOne
    private Dictionaries dictionaries;
}
