package com.example.ERP.web.controllers;

import com.example.ERP.application.services.ClienteService;
import com.example.ERP.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        return clienteService.actualizarCliente(id, clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }
}
