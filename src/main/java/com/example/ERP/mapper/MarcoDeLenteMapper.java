package com.example.ERP.mapper;

import com.example.ERP.domain.entities.MarcoDeLente;
import com.example.ERP.dto.MarcoDeLenteDTO;
import org.springframework.stereotype.Component;

@Component
public class MarcoDeLenteMapper {
    public MarcoDeLenteDTO toDTO(MarcoDeLente marco) {
        MarcoDeLenteDTO dto = new MarcoDeLenteDTO();
        dto.setId(marco.getId());
        dto.setModelo(marco.getModelo());
        dto.setMarca(marco.getMarca());
        dto.setDescripcion(marco.getDescripcion());
        dto.setCantidadDisponible(marco.getCantidadDisponible());
        dto.setPrecio(marco.getPrecio());
        dto.setEstado(marco.getEstado());
        return dto;
    }

    public MarcoDeLente toEntity(MarcoDeLenteDTO dto) {
        MarcoDeLente marco = new MarcoDeLente();
        marco.setId(dto.getId());
        marco.setModelo(dto.getModelo());
        marco.setMarca(dto.getMarca());
        marco.setDescripcion(dto.getDescripcion());
        marco.setCantidadDisponible(dto.getCantidadDisponible());
        marco.setPrecio(dto.getPrecio());
        marco.setEstado(dto.getEstado());
        return marco;
    }
}