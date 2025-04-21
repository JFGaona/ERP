package com.example.ERP.web.controllers;

import com.example.ERP.application.services.HistoriaClinicaService;
import com.example.ERP.application.services.ClienteService;
import com.example.ERP.domain.entities.HistoriaClinica;
import com.example.ERP.domain.entities.Cliente;
import com.example.ERP.dto.HistoriaClinicaDTO;
import com.example.ERP.mapper.HistoriaClinicaMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/historias")
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;
    private final ClienteService clienteService;
    private final HistoriaClinicaMapper historiaClinicaMapper;

    public HistoriaClinicaController(HistoriaClinicaService historiaClinicaService,
                                     ClienteService clienteService,
                                     HistoriaClinicaMapper historiaClinicaMapper) {
        this.historiaClinicaService = historiaClinicaService;
        this.clienteService = clienteService;
        this.historiaClinicaMapper = historiaClinicaMapper;
    }

    @GetMapping
    public ResponseEntity<List<HistoriaClinicaDTO>> obtenerTodas() {
        List<HistoriaClinicaDTO> historias = historiaClinicaService.obtenerTodas()
                .stream()
                .map(historiaClinicaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(historias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDTO> obtenerPorId(@PathVariable Long id) {
        HistoriaClinica historia = historiaClinicaService.obtenerPorId(id);
        return ResponseEntity.ok(historiaClinicaMapper.toDTO(historia));
    }

    @PostMapping
    public ResponseEntity<HistoriaClinicaDTO> crearHistoria(@Valid @RequestBody HistoriaClinicaDTO historiaDTO) {
        Cliente cliente = clienteService.obtenerPorId(historiaDTO.getClienteId());
        HistoriaClinica historia = historiaClinicaMapper.toEntity(historiaDTO, cliente);
        HistoriaClinica nueva = historiaClinicaService.crearHistoria(historia);
        return ResponseEntity.status(HttpStatus.CREATED).body(historiaClinicaMapper.toDTO(nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDTO> actualizarHistoria(@PathVariable Long id, @Valid @RequestBody HistoriaClinicaDTO historiaDTO) {
        Cliente cliente = clienteService.obtenerPorId(historiaDTO.getClienteId());
        HistoriaClinica historiaActualizada = historiaClinicaMapper.toEntity(historiaDTO, cliente);
        HistoriaClinica actualizada = historiaClinicaService.actualizarHistoria(id, historiaActualizada);
        return ResponseEntity.ok(historiaClinicaMapper.toDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistoria(@PathVariable Long id) {
        historiaClinicaService.eliminarHistoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<HistoriaClinicaDTO>> obtenerPorCliente(@PathVariable Long clienteId) {
        List<HistoriaClinicaDTO> historias = historiaClinicaService.obtenerPorClienteId(clienteId)
                .stream()
                .map(historiaClinicaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(historias);
    }
}