package com.example.ERP.application.services;

import com.example.ERP.domain.entities.Rol;
import com.example.ERP.exceptions.DuplicateResourceException;
import com.example.ERP.exceptions.ResourceNotFoundException;
import com.example.ERP.infrastructure.repositories.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new ResourceNotFoundException("Rol con ID " + id + " no encontrado"));
    }

    public Rol crearRol(Rol rol) {
        if (rolRepository.findByNombre(rol.getNombre()).isPresent()) {
            throw new DuplicateResourceException("Ya existe un rol con el nombre " + rol.getNombre());
        }
        return rolRepository.save(rol);
    }

    public Rol actualizarRol(Long id, Rol rolActualizado) {
        Rol rol = obtenerPorId(id);
        if (!rol.getNombre().equals(rolActualizado.getNombre()) &&
                rolRepository.findByNombre(rolActualizado.getNombre()).isPresent()) {
            throw new DuplicateResourceException("El nombre " + rolActualizado.getNombre() + " ya está en uso");
        }
        rol.setNombre(rolActualizado.getNombre());
        return rolRepository.save(rol);
    }

    public void eliminarRol(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rol con ID " + id + " no encontrado");
        }
        rolRepository.deleteById(id);
    }
}