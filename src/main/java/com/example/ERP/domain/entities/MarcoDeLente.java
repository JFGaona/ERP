package com.example.ERP.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "MarcoDeLente")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Integer cantidadDisponible;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(length = 10, nullable = false)
    private String estado; // Valores esperados: "DISPONIBLE" o "AGOTADO"
}
