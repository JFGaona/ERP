package com.example.ERP.application.services;

import com.example.ERP.domain.entities.Cliente;
import com.example.ERP.infrastructure.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
