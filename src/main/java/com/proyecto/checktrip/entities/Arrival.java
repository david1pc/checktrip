package com.proyecto.checktrip.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Entity(name = "Arrival")
public class Arrival {
    @Id
    @EqualsAndHashCode.Include
    private String iataCode;

    private String terminal;

    private LocalDateTime at;
}
