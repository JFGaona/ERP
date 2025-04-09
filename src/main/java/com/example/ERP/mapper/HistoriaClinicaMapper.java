package com.example.ERP.mapper;

import com.example.ERP.domain.entities.Cliente;
import com.example.ERP.domain.entities.HistoriaClinica;
import com.example.ERP.dto.HistoriaClinicaDTO;
import org.springframework.stereotype.Component;

@Component
public class HistoriaClinicaMapper {
    public HistoriaClinicaDTO toDTO(HistoriaClinica historia) {
        HistoriaClinicaDTO dto = new HistoriaClinicaDTO();
        dto.setId(historia.getId());
        dto.setDescripcion(historia.getDescripcion());
        dto.setFechaConsulta(historia.getFechaConsulta());
        dto.setClienteId(historia.getCliente().getId());
        return dto;
    }

    public HistoriaClinica toEntity(HistoriaClinicaDTO dto, Cliente cliente) {
        HistoriaClinica historia = new HistoriaClinica();
        historia.setId(dto.getId());
        historia.setDescripcion(dto.getDescripcion());
        historia.setFechaConsulta(dto.getFechaConsulta());
        historia.setCliente(cliente);
        return historia;
    }
}