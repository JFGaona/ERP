package com.example.ERP.application.services;

import com.example.ERP.domain.entities.HistoriaClinica;
import com.example.ERP.exceptions.ResourceNotFoundException;
import com.example.ERP.infrastructure.repositories.HistoriaClinicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final ClienteService clienteService;

    public HistoriaClinicaService(HistoriaClinicaRepository historiaClinicaRepository, ClienteService clienteService) {
        this.historiaClinicaRepository = historiaClinicaRepository;
        this.clienteService = clienteService;
    }

    public List<HistoriaClinica> obtenerTodas() {
        return historiaClinicaRepository.findAll();
    }

    public HistoriaClinica obtenerPorId(Long id) {
        return historiaClinicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historia clínica con ID " + id + " no encontrada"));
    }

    public HistoriaClinica crearHistoria(HistoriaClinica historia) {
        clienteService.obtenerPorId(historia.getCliente().getId());
        return historiaClinicaRepository.save(historia);
    }

    public HistoriaClinica actualizarHistoria(Long id, HistoriaClinica historiaActualizada) {
        HistoriaClinica historia = obtenerPorId(id);
        clienteService.obtenerPorId(historiaActualizada.getCliente().getId());
        historia.setDescripcion(historiaActualizada.getDescripcion());
        historia.setFechaConsulta(historiaActualizada.getFechaConsulta());
        historia.setCliente(historiaActualizada.getCliente());
        return historiaClinicaRepository.save(historia);
    }

    public void eliminarHistoria(Long id) {
        if (!historiaClinicaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Historia clínica con ID " + id + " no encontrada");
        }
        historiaClinicaRepository.deleteById(id);
    }

    public List<HistoriaClinica> obtenerPorClienteId(Long clienteId) {
        clienteService.obtenerPorId(clienteId);
        return historiaClinicaRepository.findByClienteId(clienteId);
    }
}