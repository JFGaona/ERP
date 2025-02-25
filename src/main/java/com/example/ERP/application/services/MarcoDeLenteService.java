package com.example.ERP.application.services;

import com.example.ERP.domain.entities.MarcoDeLente;
import com.example.ERP.infrastructure.repositories.MarcoDeLenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcoDeLenteService {

    @Autowired
    private MarcoDeLenteRepository marcoRepository;

    public List<MarcoDeLente> obtenerTodos() {
        return marcoRepository.findAll();
    }

    public Optional<MarcoDeLente> obtenerPorId(Long id) {
        return marcoRepository.findById(id);
    }

    public List<MarcoDeLente> obtenerPorModelo(String modelo) {
        return marcoRepository.findByModelo(modelo);
    }

    public List<MarcoDeLente> obtenerDisponibles() {
        return marcoRepository.findByEstado("DISPONIBLE");
    }

    public MarcoDeLente crearMarco(MarcoDeLente marco) {
        return marcoRepository.save(marco);
    }

    public MarcoDeLente actualizarMarco(Long id, MarcoDeLente marcoActualizado) {
        return marcoRepository.findById(id).map(marco -> {
            marco.setModelo(marcoActualizado.getModelo());
            marco.setMarca(marcoActualizado.getMarca());
            marco.setCantidadDisponible(marcoActualizado.getCantidadDisponible());
            marco.setEstado(marcoActualizado.getEstado());
            return marcoRepository.save(marco);
        }).orElseThrow(() -> new RuntimeException("Marco no encontrado"));
    }

    public void eliminarMarco(Long id) {
        marcoRepository.deleteById(id);
    }
}
