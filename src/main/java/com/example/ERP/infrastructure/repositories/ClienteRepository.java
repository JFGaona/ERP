package com.example.ERP.infrastructure.repositories;

import com.example.ERP.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Método para buscar clientes por email
    Cliente findByEmail(String email);
}
