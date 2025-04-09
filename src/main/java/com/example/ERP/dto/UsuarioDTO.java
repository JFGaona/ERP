package com.example.ERP.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @Email(message = "El email no tiene un formato válido")
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
    private Long rolId;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Long getRolId() { return rolId; }
    public void setRolId(Long rolId) { this.rolId = rolId; }
}