package com.example.ERP.infrastructure.repositories;

import com.example.ERP.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCedula(String cedula);
    boolean existsByCedula(String cedula);
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(c.apellido) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR c.cedula LIKE CONCAT('%', :searchTerm, '%')")
    List<Cliente> searchByNombreOrApellidoOrCedula(@Param("searchTerm") String searchTerm);
}