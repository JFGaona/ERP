package com.example.ERP.web.controllers;

import com.example.ERP.application.services.ClienteService;
import com.example.ERP.domain.entities.Cliente;
import com.example.ERP.dto.ClienteDTO;
import com.example.ERP.mapper.ClienteMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodos() {
        List<ClienteDTO> clientes = clienteService.obtenerTodos()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(clienteMapper.toDTO(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente creado = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.toDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteActualizado = clienteMapper.toEntity(clienteDTO);
        Cliente actualizado = clienteService.actualizarCliente(id, clienteActualizado);
        return ResponseEntity.ok(clienteMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}