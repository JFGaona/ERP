package com.example.ERP.mapper;

import com.example.ERP.domain.entities.Rol;
import com.example.ERP.domain.entities.Usuario;
import com.example.ERP.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setEmail(usuario.getEmail());
        dto.setPassword(usuario.getPassword());
        dto.setRolId(usuario.getRol().getId());
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto, Rol rol) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(rol);
        return usuario;
    }
}