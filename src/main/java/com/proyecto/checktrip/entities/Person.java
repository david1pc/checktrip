package com.proyecto.checktrip.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    private String nombres;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private String correo;
    private String username;
    private String password;
}
