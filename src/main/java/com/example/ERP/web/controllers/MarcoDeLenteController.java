package com.example.ERP.web.controllers;

import com.example.ERP.domain.entities.MarcoDeLente;
import com.example.ERP.application.services.MarcoDeLenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcos")
public class MarcoDeLenteController {

    private final MarcoDeLenteService marcoDeLenteService;

    public MarcoDeLenteController(MarcoDeLenteService marcoDeLenteService) {
        this.marcoDeLenteService = marcoDeLenteService;
    }

    @GetMapping
    public ResponseEntity<List<MarcoDeLente>> obtenerTodos() {
        return ResponseEntity.ok(marcoDeLenteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcoDeLente> obtenerPorId(@PathVariable Long id) {
        MarcoDeLente marco = marcoDeLenteService.obtenerPorId(id);
        return ResponseEntity.ok(marco);
    }

    @PostMapping
    public ResponseEntity<MarcoDeLente> crearMarco(@RequestBody MarcoDeLente marco) {
        MarcoDeLente nuevo = marcoDeLenteService.crearMarco(marco);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcoDeLente> actualizarMarco(@PathVariable Long id, @RequestBody MarcoDeLente marco) {
        MarcoDeLente actualizado = marcoDeLenteService.actualizarMarco(id, marco);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarco(@PathVariable Long id) {
        marcoDeLenteService.eliminarMarco(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para buscar marcos por modelo (búsqueda parcial)
    @GetMapping("/buscar/modelo")
    public ResponseEntity<List<MarcoDeLente>> obtenerPorModelo(@RequestParam String modelo) {
        List<MarcoDeLente> marcos = marcoDeLenteService.obtenerPorModelo(modelo);
        return ResponseEntity.ok(marcos);
    }

    // Endpoint para buscar marcos por estado
    @GetMapping("/buscar/estado")
    public ResponseEntity<List<MarcoDeLente>> obtenerPorEstado(@RequestParam String estado) {
        List<MarcoDeLente> marcos = marcoDeLenteService.obtenerPorEstado(estado);
        return ResponseEntity.ok(marcos);
    }
}
