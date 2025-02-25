package com.example.ERP.infrastructure.repositories;

import com.example.ERP.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar un cliente por su cédula
    Optional<Cliente> findByCedula(String cedula);
}
