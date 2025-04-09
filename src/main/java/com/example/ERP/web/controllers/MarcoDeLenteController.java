package com.example.ERP.web.controllers;

import com.example.ERP.application.services.MarcoDeLenteService;
import com.example.ERP.domain.entities.MarcoDeLente;
import com.example.ERP.dto.MarcoDeLenteDTO;
import com.example.ERP.mapper.MarcoDeLenteMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/marcos")
public class MarcoDeLenteController {

    private final MarcoDeLenteService marcoDeLenteService;
    private final MarcoDeLenteMapper marcoDeLenteMapper;

    public MarcoDeLenteController(MarcoDeLenteService marcoDeLenteService, MarcoDeLenteMapper marcoDeLenteMapper) {
        this.marcoDeLenteService = marcoDeLenteService;
        this.marcoDeLenteMapper = marcoDeLenteMapper;
    }

    @GetMapping
    public ResponseEntity<List<MarcoDeLenteDTO>> obtenerTodos() {
        List<MarcoDeLenteDTO> marcos = marcoDeLenteService.obtenerTodos()
                .stream()
                .map(marcoDeLenteMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(marcos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcoDeLenteDTO> obtenerPorId(@PathVariable Long id) {
        MarcoDeLente marco = marcoDeLenteService.obtenerPorId(id);
        return ResponseEntity.ok(marcoDeLenteMapper.toDTO(marco));
    }

    @PostMapping
    public ResponseEntity<MarcoDeLenteDTO> crearMarco(@Valid @RequestBody MarcoDeLenteDTO marcoDTO) {
        MarcoDeLente marco = marcoDeLenteMapper.toEntity(marcoDTO);
        MarcoDeLente nuevo = marcoDeLenteService.crearMarco(marco);
        return ResponseEntity.status(HttpStatus.CREATED).body(marcoDeLenteMapper.toDTO(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcoDeLenteDTO> actualizarMarco(@PathVariable Long id, @Valid @RequestBody MarcoDeLenteDTO marcoDTO) {
        MarcoDeLente marcoActualizado = marcoDeLenteMapper.toEntity(marcoDTO);
        MarcoDeLente actualizado = marcoDeLenteService.actualizarMarco(id, marcoActualizado);
        return ResponseEntity.ok(marcoDeLenteMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarco(@PathVariable Long id) {
        marcoDeLenteService.eliminarMarco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/modelo")
    public ResponseEntity<List<MarcoDeLenteDTO>> obtenerPorModelo(@RequestParam String modelo) {
        List<MarcoDeLenteDTO> marcos = marcoDeLenteService.obtenerPorModelo(modelo)
                .stream()
                .map(marcoDeLenteMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(marcos);
    }

    @GetMapping("/buscar/estado")
    public ResponseEntity<List<MarcoDeLenteDTO>> obtenerPorEstado(@RequestParam String estado) {
        List<MarcoDeLenteDTO> marcos = marcoDeLenteService.obtenerPorEstado(estado)
                .stream()
                .map(marcoDeLenteMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(marcos);
    }
}