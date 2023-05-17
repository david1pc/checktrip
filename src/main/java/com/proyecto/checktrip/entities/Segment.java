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
@Entity(name = "Segment")
public class Segment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String carrierCode;
    private String number;
    private String duration;
    private String id;
    private Integer numberOfStops;
    private Boolean blackListedInEU;
    @ManyToOne
    private Itinerary itinerary;
    @OneToOne
    private Arrival departure;
    @OneToOne
    private Arrival arrival;
    @OneToOne
    private SegmentAircraft aircraft;
    @OneToOne
    private Operating operating;
}
