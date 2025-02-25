package com.example.ERP.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double precio;

    @Column(length = 10, nullable = false)
    private String estado = "DISPONIBLE";

    public MarcoDeLente(String modelo, String marca, String descripcion, int cantidadDisponible, Double precio) {
        this.modelo = modelo;
        this.marca = marca;
        this.descripcion = descripcion;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
        this.estado = "DISPONIBLE";
    }
}
