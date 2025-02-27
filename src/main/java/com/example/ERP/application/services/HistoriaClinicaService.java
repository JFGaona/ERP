package com.example.ERP.application.services;

import com.example.ERP.domain.entities.HistoriaClinica;
import com.example.ERP.infrastructure.repositories.HistoriaClinicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;

    public HistoriaClinicaService(HistoriaClinicaRepository historiaClinicaRepository) {
        this.historiaClinicaRepository = historiaClinicaRepository;
    }

    public List<HistoriaClinica> obtenerTodas() {
        return historiaClinicaRepository.findAll();
    }

    public HistoriaClinica obtenerPorId(Long id) {
        return historiaClinicaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("HistoriaClinica no encontrada con id: " + id));
    }

    public HistoriaClinica crearHistoria(HistoriaClinica historia) {
        return historiaClinicaRepository.save(historia);
    }

    public HistoriaClinica actualizarHistoria(Long id, HistoriaClinica historia) {
        HistoriaClinica existente = obtenerPorId(id);
        existente.setDescripcion(historia.getDescripcion());
        existente.setFechaConsulta(historia.getFechaConsulta());
        existente.setCliente(historia.getCliente());
        return historiaClinicaRepository.save(existente);
    }

    public void eliminarHistoria(Long id) {
        if (!historiaClinicaRepository.existsById(id)) {
            throw new NoSuchElementException("HistoriaClinica no encontrada con id: " + id);
        }
        historiaClinicaRepository.deleteById(id);
    }

    // Nuevo método: Obtener historias clínicas por el ID del cliente
    public List<HistoriaClinica> obtenerPorClienteId(Long clienteId) {
        return historiaClinicaRepository.findByClienteId(clienteId);
    }
}
