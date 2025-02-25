package com.example.ERP.infrastructure.repositories;

import com.example.ERP.domain.entities.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {

    // Obtener todas las historias clínicas de un cliente específico
    List<HistoriaClinica> findByClienteId(Long clienteId);
}
