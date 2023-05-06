package com.proyecto.checktrip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(name = "client")
@Entity(name = "Client")
public class Client extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<RoleClient> roles;

    public Client(String nombres, String apellidos, LocalDate fecha_nacimiento, String correo, String username, String password) {
        super(nombres, apellidos, fecha_nacimiento, correo, username, password);
    }
}
