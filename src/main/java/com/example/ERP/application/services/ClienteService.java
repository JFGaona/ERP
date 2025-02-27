package com.example.ERP.application.services;

import com.example.ERP.domain.entities.Cliente;
import com.example.ERP.infrastructure.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Obtiene la lista de todos los clientes
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    // Obtiene un cliente por su ID
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Crea un nuevo cliente, validando que no exista ya con la misma cédula
    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByCedula(cliente.getCedula())) {
            throw new IllegalArgumentException("Ya existe un cliente con la cédula: " + cliente.getCedula());
        }
        return clienteRepository.save(cliente);
    }

    // Actualiza los datos de un cliente existente
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado con id: " + id));

        // Actualizar solo los campos permitidos (la cédula usualmente no se actualiza)
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefono(cliente.getTelefono());

        return clienteRepository.save(clienteExistente);
    }

    // Elimina un cliente existente
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
