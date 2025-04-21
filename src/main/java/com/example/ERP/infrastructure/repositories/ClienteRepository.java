package com.example.ERP.infrastructure.repositories;

import com.example.ERP.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCedula(String cedula);
    boolean existsByCedula(String cedula);
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);
}