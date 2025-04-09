package com.example.ERP.mapper;

import com.example.ERP.domain.entities.Rol;
import com.example.ERP.dto.RolDTO;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {
    public RolDTO toDTO(Rol rol) {
        RolDTO dto = new RolDTO();
        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());
        return dto;
    }

    public Rol toEntity(RolDTO dto) {
        Rol rol = new Rol();
        rol.setId(dto.getId());
        rol.setNombre(dto.getNombre());
        return rol;
    }
}