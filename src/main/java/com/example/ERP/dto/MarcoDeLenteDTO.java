package com.example.ERP.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class MarcoDeLenteDTO {
    private Long id;
    @NotBlank(message = "El modelo no puede estar vacío")
    private String modelo;
    private String marca;
    private String descripcion;
    @NotNull(message = "La cantidad disponible no puede ser nula")
    @Min(value = 0)
    private Integer cantidadDisponible;
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01")
    private BigDecimal precio;
    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(regexp = "DISPONIBLE|AGOTADO")
    private String estado;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(Integer cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}