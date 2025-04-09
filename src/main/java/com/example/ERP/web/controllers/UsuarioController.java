package com.example.ERP.web.controllers;

import com.example.ERP.application.services.UsuarioService;
import com.example.ERP.application.services.RolService;
import com.example.ERP.domain.entities.Usuario;
import com.example.ERP.domain.entities.Rol;
import com.example.ERP.dto.UsuarioDTO;
import com.example.ERP.mapper.UsuarioMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService, RolService rolService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodos() {
        List<UsuarioDTO> usuarios = usuarioService.obtenerTodos()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuarioMapper.toDTO(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Rol rol = rolService.obtenerPorId(usuarioDTO.getRolId());
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO, rol);
        Usuario creado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.toDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        Rol rol = rolService.obtenerPorId(usuarioDTO.getRolId());
        Usuario usuarioActualizado = usuarioMapper.toEntity(usuarioDTO, rol);
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuarioActualizado);
        return ResponseEntity.ok(usuarioMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}