package com.example.ERP.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "MarcoDeLente")
@Getter
@Setter
@NoArgsConstructor
public class MarcoDeLente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String modelo;

    @Column(length = 100)
    private String marca;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private int cantidadDisponible;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(length = 10, nullable = false)
    private String estado = "DISPONIBLE";

}