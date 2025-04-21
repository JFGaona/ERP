package com.example.ERP.infrastructure.repositories;

import com.example.ERP.domain.entities.MarcoDeLente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcoDeLenteRepository extends JpaRepository<MarcoDeLente, Long> {
    List<MarcoDeLente> findByModeloContainingIgnoreCase(String modelo);
    List<MarcoDeLente> findByEstado(String estado);
}