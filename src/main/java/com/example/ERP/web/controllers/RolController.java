package com.example.ERP.web.controllers;

import com.example.ERP.application.services.RolService;
import com.example.ERP.domain.entities.Rol;
import com.example.ERP.dto.RolDTO;
import com.example.ERP.mapper.RolMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;
    private final RolMapper rolMapper;

    public RolController(RolService rolService, RolMapper rolMapper) {
        this.rolService = rolService;
        this.rolMapper = rolMapper;
    }

    @GetMapping
    public ResponseEntity<List<RolDTO>> obtenerTodos() {
        List<RolDTO> roles = rolService.obtenerTodos()
                .stream()
                .map(rolMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> obtenerPorId(@PathVariable Long id) {
        Rol rol = rolService.obtenerPorId(id);
        return ResponseEntity.ok(rolMapper.toDTO(rol));
    }

    @PostMapping
    public ResponseEntity<RolDTO> crearRol(@Valid @RequestBody RolDTO rolDTO) {
        Rol rol = rolMapper.toEntity(rolDTO);
        Rol creado = rolService.crearRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolMapper.toDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> actualizarRol(@PathVariable Long id, @Valid @RequestBody RolDTO rolDTO) {
        Rol rolActualizado = rolMapper.toEntity(rolDTO);
        Rol actualizado = rolService.actualizarRol(id, rolActualizado);
        return ResponseEntity.ok(rolMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}