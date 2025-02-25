package com.example.ERP.web.controllers;

import com.example.ERP.application.services.ClienteService;
import com.example.ERP.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = clienteService.obtenerTodos();
        System.out.println("Clientes obtenidos: " + clientes); // Para depuración
        return clientes;
    }
    @GetMapping("/{id}")
    public Optional<Cliente> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }
}
