package com.example.ERP.web.controllers;

import com.example.ERP.domain.entities.HistoriaClinica;
import com.example.ERP.infrastructure.repositories.HistoriaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historias")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @GetMapping
    public List<HistoriaClinica> obtenerHistorias() {
        return historiaClinicaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<HistoriaClinica> obtenerHistoriaPorId(@PathVariable Long id) {
        return historiaClinicaRepository.findById(id);
    }

    @PostMapping
    public HistoriaClinica crearHistoria(@RequestBody HistoriaClinica historia) {
        return historiaClinicaRepository.save(historia);
    }


    @DeleteMapping("/{id}")
    public void eliminarHistoria(@PathVariable Long id) {
        historiaClinicaRepository.deleteById(id);
    }
}
