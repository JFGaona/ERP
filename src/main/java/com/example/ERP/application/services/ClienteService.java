package com.example.ERP.application.services;

import com.example.ERP.domain.entities.Cliente;
import com.example.ERP.exceptions.DuplicateResourceException;
import com.example.ERP.exceptions.ResourceNotFoundException;
import com.example.ERP.infrastructure.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + id + " no encontrado"));
    }

    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByCedula(cliente.getCedula())) {
            throw new DuplicateResourceException("Ya existe un cliente con la cédula " + cliente.getCedula());
        }
        if (cliente.getEmail() != null && clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new DuplicateResourceException("Ya existe un cliente con el email " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente cliente = obtenerPorId(id);
        if (!cliente.getCedula().equals(clienteActualizado.getCedula()) &&
                clienteRepository.existsByCedula(clienteActualizado.getCedula())) {
            throw new DuplicateResourceException("La cédula " + clienteActualizado.getCedula() + " ya está en uso");
        }
        if (clienteActualizado.getEmail() != null && !clienteActualizado.getEmail().equals(cliente.getEmail()) &&
                clienteRepository.existsByEmail(clienteActualizado.getEmail())) {
            throw new DuplicateResourceException("El email " + clienteActualizado.getEmail() + " ya está en uso");
        }
        cliente.setCedula(clienteActualizado.getCedula());
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setApellido(clienteActualizado.getApellido());
        cliente.setEmail(clienteActualizado.getEmail());
        cliente.setTelefono(clienteActualizado.getTelefono());
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente con ID " + id + " no encontrado");
        }
        clienteRepository.deleteById(id);
    }

    public List<Cliente> buscarPorNombreOrApellidoOrCedula(String searchTerm) {
        return clienteRepository.searchByNombreOrApellidoOrCedula(searchTerm);
    }
}