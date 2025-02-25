package com.example.ERP.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")  // Asegúrate de que el nombre de la tabla coincide con la de la BD
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Si la BD tiene autoincrement
    private Long id;

    @Column(name = "nombre", nullable = false)  // Coincide con la BD
    private String nombre;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    public Cliente() {}

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
