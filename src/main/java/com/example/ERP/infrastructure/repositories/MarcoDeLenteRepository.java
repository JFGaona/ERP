package com.example.ERP.infrastructure.repositories;

import com.example.ERP.domain.entities.MarcoDeLente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcoDeLenteRepository extends JpaRepository<MarcoDeLente, Long> {

    // Buscar marcos de un modelo específico
    List<MarcoDeLente> findByModeloContainingIgnoreCase(String modelo);
    // Obtener todos los marcos disponibles
    List<MarcoDeLente> findByEstado(String estado);
}
