package com.example.ERP.web.controllers;

import com.example.ERP.domain.entities.Cliente;
import com.example.ERP.infrastructure.repositories.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Endpoint para obtener todos los clientes
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Endpoint para buscar un cliente por email
    @GetMapping("/{email}")
    public Cliente getClienteByEmail(@PathVariable String email) {
        return clienteRepository.findByEmail(email);
    }
}
