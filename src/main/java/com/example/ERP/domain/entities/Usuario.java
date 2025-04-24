package com.example.ERP.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Usuario",schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Usuario extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String telefono;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiado de LAZY a EAGER
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
}