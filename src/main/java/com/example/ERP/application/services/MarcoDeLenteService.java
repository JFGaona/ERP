package com.example.ERP.application.services;

import com.example.ERP.domain.entities.MarcoDeLente;
import com.example.ERP.infrastructure.repositories.MarcoDeLenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
                .orElseThrow(() -> new NoSuchElementException("MarcoDeLente no encontrado con id: " + id));
    }

    public MarcoDeLente crearMarco(MarcoDeLente marco) {
        return marcoDeLenteRepository.save(marco);
    }

    public MarcoDeLente actualizarMarco(Long id, MarcoDeLente marco) {
        MarcoDeLente existente = obtenerPorId(id);
        existente.setModelo(marco.getModelo());
        existente.setMarca(marco.getMarca());
        existente.setDescripcion(marco.getDescripcion());
        existente.setCantidadDisponible(marco.getCantidadDisponible());
        existente.setPrecio(marco.getPrecio());
        existente.setEstado(marco.getEstado());
        return marcoDeLenteRepository.save(existente);
    }

    public void eliminarMarco(Long id) {
        if (!marcoDeLenteRepository.existsById(id)) {
            throw new NoSuchElementException("MarcoDeLente no encontrado con id: " + id);
        }
        marcoDeLenteRepository.deleteById(id);
    }

    // Nuevo método: Buscar marcos por modelo (búsqueda parcial, ignorando mayúsculas/minúsculas)
    public List<MarcoDeLente> obtenerPorModelo(String modelo) {
        return marcoDeLenteRepository.findByModeloContainingIgnoreCase(modelo);
    }

    // Nuevo método: Buscar marcos por estado
    public List<MarcoDeLente> obtenerPorEstado(String estado) {
        return marcoDeLenteRepository.findByEstado(estado);
    }
}
