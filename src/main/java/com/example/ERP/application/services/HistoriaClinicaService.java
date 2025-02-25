package com.example.ERP.application.services;

import com.example.ERP.domain.entities.HistoriaClinica;
import com.example.ERP.infrastructure.repositories.HistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    public List<HistoriaClinica> obtenerTodas() {
        return historiaClinicaRepository.findAll();
    }

    public Optional<HistoriaClinica> obtenerPorId(Long id) {
        return historiaClinicaRepository.findById(id);
    }

    public List<HistoriaClinica> obtenerPorCliente(Long clienteId) {
        return historiaClinicaRepository.findByClienteId(clienteId);
    }

    public HistoriaClinica crearHistoria(HistoriaClinica historia) {
        return historiaClinicaRepository.save(historia);
    }

    public void eliminarHistoria(Long id) {
        historiaClinicaRepository.deleteById(id);
    }
}
