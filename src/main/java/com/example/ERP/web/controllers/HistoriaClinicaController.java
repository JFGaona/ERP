package com.example.ERP.web.controllers;

import com.example.ERP.domain.entities.HistoriaClinica;
import com.example.ERP.application.services.HistoriaClinicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historias")
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    public HistoriaClinicaController(HistoriaClinicaService historiaClinicaService) {
        this.historiaClinicaService = historiaClinicaService;
    }

    @GetMapping
    public ResponseEntity<List<HistoriaClinica>> obtenerTodas() {
        return ResponseEntity.ok(historiaClinicaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> obtenerPorId(@PathVariable Long id) {
        HistoriaClinica historia = historiaClinicaService.obtenerPorId(id);
        return ResponseEntity.ok(historia);
    }

    @PostMapping
    public ResponseEntity<HistoriaClinica> crearHistoria(@RequestBody HistoriaClinica historia) {
        HistoriaClinica nueva = historiaClinicaService.crearHistoria(historia);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> actualizarHistoria(@PathVariable Long id, @RequestBody HistoriaClinica historia) {
        HistoriaClinica actualizada = historiaClinicaService.actualizarHistoria(id, historia);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistoria(@PathVariable Long id) {
        historiaClinicaService.eliminarHistoria(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener historias por ID de cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<HistoriaClinica>> obtenerPorCliente(@PathVariable Long clienteId) {
        List<HistoriaClinica> historias = historiaClinicaService.obtenerPorClienteId(clienteId);
        return ResponseEntity.ok(historias);
    }
}
