package com.example.ERP.application.services;

import com.example.ERP.domain.entities.Rol;
import com.example.ERP.infrastructure.repositories.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    public Rol obtenerPorId(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con id: " + id));
    }

    public Rol crearRol(Rol rol) {
        if (rolRepository.findByNombre(rol.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un rol con el nombre: " + rol.getNombre());
        }
        return rolRepository.save(rol);
    }

    public Rol actualizarRol(Long id, Rol rol) {
        Rol existente = obtenerPorId(id);
        existente.setNombre(rol.getNombre());
        return rolRepository.save(existente);
    }

    public void eliminarRol(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new NoSuchElementException("Rol no encontrado con id: " + id);
        }
        rolRepository.deleteById(id);
    }
}
