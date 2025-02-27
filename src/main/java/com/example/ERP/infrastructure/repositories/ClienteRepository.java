package com.example.ERP.infrastructure.repositories;

import com.example.ERP.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar un cliente por su cédula
    Optional<Cliente> findByCedula(String cedula);

    // Verificar si existe un cliente con la cédula especificada
    boolean existsByCedula(String cedula);

    // Buscar un cliente por email
    Optional<Cliente> findByEmail(String email);

    // Buscar clientes cuyo apellido contenga una cadena (sin distinguir mayúsculas/minúsculas)
    List<Cliente> findByApellidoContainingIgnoreCase(String apellido);

    // Ejemplo de consulta JPQL personalizada: obtener clientes registrados entre dos fechas
    @Query("SELECT c FROM Cliente c WHERE c.fechaRegistro BETWEEN :startDate AND :endDate")
    List<Cliente> findClientesByFechaRegistroBetween(LocalDateTime startDate, LocalDateTime endDate);
}
