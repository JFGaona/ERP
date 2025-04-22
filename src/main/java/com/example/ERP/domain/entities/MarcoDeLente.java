package com.example.ERP.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table(name = "MarcoDeLente", schema = "dbo")
@Data
@EqualsAndHashCode(callSuper=false)
public class MarcoDeLente extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El modelo no puede estar vacío")
    @Size(max = 100, message = "El modelo no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String modelo;

    @Size(max = 100, message = "La marca no puede exceder los 100 caracteres")
    @Column(length = 100)
    private String marca;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "La cantidad disponible no puede ser nula")
    @Min(value = 0, message = "La cantidad disponible no puede ser negativa")
    @Column(nullable = false)
    private Integer cantidadDisponible;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(regexp = "DISPONIBLE|AGOTADO", message = "El estado debe ser 'DISPONIBLE' o 'AGOTADO'")
    @Column(length = 10, nullable = false)
    private String estado;
}