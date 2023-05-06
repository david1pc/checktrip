package com.proyecto.checktrip.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(name = "roleClient")
@Entity(name = "RoleClient")
public class RoleClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @OneToOne
    private Role role;

    @ManyToOne
    private Client client;

    public RoleClient(Role role, Client client) {
        this.role = role;
        this.client = client;
    }
}
