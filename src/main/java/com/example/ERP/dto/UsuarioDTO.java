package com.example.ERP.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String username;
    @Email(message = "El email no tiene un formato válido")
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 5, message = "La contraseña debe tener al menos 5 caracteres")
    private String password;
    private Long rolId;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Long getRolId() { return rolId; }
    public void setRolId(Long rolId) { this.rolId = rolId; }
}