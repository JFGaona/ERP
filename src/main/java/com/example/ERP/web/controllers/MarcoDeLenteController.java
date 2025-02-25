package com.example.ERP.web.controllers;

import com.example.ERP.domain.entities.MarcoDeLente;
import com.example.ERP.infrastructure.repositories.MarcoDeLenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcos")
public class MarcoDeLenteController {

    @Autowired
    private MarcoDeLenteRepository marcoRepository;

    @GetMapping
    public List<MarcoDeLente> obtenerMarcos() {
        return marcoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MarcoDeLente> obtenerMarcoPorId(@PathVariable Long id) {
        return marcoRepository.findById(id);
    }

    @PostMapping
    public MarcoDeLente crearMarco(@RequestBody MarcoDeLente marco) {
        return marcoRepository.save(marco);
    }

    @PutMapping("/{id}")
    public MarcoDeLente actualizarMarco(@PathVariable Long id, @RequestBody MarcoDeLente marcoActualizado) {
        return marcoRepository.findById(id).map(marco -> {
            marco.setModelo(marcoActualizado.getModelo());
            marco.setMarca(marcoActualizado.getMarca());
            marco.setCantidadDisponible(marcoActualizado.getCantidadDisponible());
            return marcoRepository.save(marco);
        }).orElseThrow(() -> new RuntimeException("Marco no encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminarMarco(@PathVariable Long id) {
        marcoRepository.deleteById(id);
    }
}
