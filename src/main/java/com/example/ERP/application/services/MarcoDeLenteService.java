package com.example.ERP.application.services;

import com.example.ERP.domain.entities.MarcoDeLente;
import com.example.ERP.exceptions.ResourceNotFoundException;
import com.example.ERP.infrastructure.repositories.MarcoDeLenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcoDeLenteService {

    private final MarcoDeLenteRepository marcoDeLenteRepository;

    public MarcoDeLenteService(MarcoDeLenteRepository marcoDeLenteRepository) {
        this.marcoDeLenteRepository = marcoDeLenteRepository;
    }

    public List<MarcoDeLente> obtenerTodos() {
        return marcoDeLenteRepository.findAll();
    }

    public MarcoDeLente obtenerPorId(Long id) {
        return marcoDeLenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marco de lente con ID " + id + " no encontrado"));
    }

    public MarcoDeLente crearMarco(MarcoDeLente marco) {
        return marcoDeLenteRepository.save(marco);
    }

    public MarcoDeLente actualizarMarco(Long id, MarcoDeLente marcoActualizado) {
        MarcoDeLente marco = obtenerPorId(id);
        marco.setModelo(marcoActualizado.getModelo());
        marco.setMarca(marcoActualizado.getMarca());
        marco.setDescripcion(marcoActualizado.getDescripcion());
        marco.setCantidadDisponible(marcoActualizado.getCantidadDisponible());
        marco.setPrecio(marcoActualizado.getPrecio());
        marco.setEstado(marcoActualizado.getEstado());
        return marcoDeLenteRepository.save(marco);
    }

    public void eliminarMarco(Long id) {
        if (!marcoDeLenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Marco de lente con ID " + id + " no encontrado");
        }
        marcoDeLenteRepository.deleteById(id);
    }

    public List<MarcoDeLente> obtenerPorModelo(String modelo) {
        return marcoDeLenteRepository.findByModeloContainingIgnoreCase(modelo);
    }

    public List<MarcoDeLente> obtenerPorEstado(String estado) {
        if (!"DISPONIBLE".equalsIgnoreCase(estado) && !"AGOTADO".equalsIgnoreCase(estado)) {
            throw new IllegalArgumentException("Estado inválido: " + estado);
        }
        return marcoDeLenteRepository.findByEstado(estado.toUpperCase());
    }
}