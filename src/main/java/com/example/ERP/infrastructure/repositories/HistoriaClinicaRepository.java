package com.example.ERP.infrastructure.repositories;

import com.example.ERP.domain.entities.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {
    List<HistoriaClinica> findByClienteId(Long clienteId);
}