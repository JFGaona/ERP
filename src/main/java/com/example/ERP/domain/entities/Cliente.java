package com.example.ERP.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La cédula no puede estar vacía")
    @Size(min = 5, max = 20, message = "La cédula debe tener entre 5 y 20 caracteres")
    @Column(nullable = false, unique = true, length = 20)
    private String cedula;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String apellido;

    @Email(message = "El email no tiene un formato válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    @Column(unique = true, length = 100)
    private String email;

    @Size(max = 20, message = "El teléfono no puede exceder los 20 caracteres")
    @Column(length = 20)
    private String telefono;
}