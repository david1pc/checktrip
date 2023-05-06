package com.proyecto.checktrip.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@MappedSuperclass
@ToString
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    public Person(String nombres, String apellidos, LocalDate fecha_nacimiento) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Person(String nombres, String apellidos, LocalDate fecha_nacimiento, String correo, String username, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo = correo;
        this.username = username;
        this.password = password;
    }
}
