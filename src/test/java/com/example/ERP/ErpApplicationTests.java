package com.example.ERP;

import com.example.ERP.application.services.ClienteService;
import com.example.ERP.domain.entities.Cliente;
import com.example.ERP.infrastructure.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ErpApplicationTests {

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	private Cliente cliente;

	@BeforeEach
	void setUp() {
		// Inicializar un cliente de prueba
		cliente = new Cliente();
		cliente.setCedula("1234567890");
		cliente.setNombre("Juan");
		cliente.setApellido("Pérez");
		cliente.setEmail("juan@example.com");
		cliente.setTelefono("123456789");
	}

	@Test
	void testCrearCliente_ValidClient_Success() {
		// Arrange: Configurar el mock
		when(clienteRepository.existsByCedula("1234567890")).thenReturn(false);
		when(clienteRepository.existsByEmail("juan@example.com")).thenReturn(false);
		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

		// Act: Invocar el método
		Cliente result = clienteService.crearCliente(cliente);

		// Assert: Verificar resultados
		assertNotNull(result, "El cliente retornado no debe ser null");
		assertEquals("1234567890", result.getCedula(), "La cédula debe coincidir");
		assertEquals("Juan", result.getNombre(), "El nombre debe coincidir");
		assertEquals("Pérez", result.getApellido(), "El apellido debe coincidir");
		assertEquals("juan@example.com", result.getEmail(), "El email debe coincidir");
		verify(clienteRepository, times(1)).save(any(Cliente.class));
		verify(clienteRepository, times(1)).existsByCedula("1234567890");
		verify(clienteRepository, times(1)).existsByEmail("juan@example.com");
	}

	@Test
	void testObtenerPorId_ExistingId_ReturnsClient() {
		// Arrange: Configurar datos de prueba
		Long id = 1L;
		cliente.setId(id);

		// Mockear comportamiento del repositorio
		when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

		// Act: Invocar el método
		Cliente result = clienteService.obtenerPorId(id);

		// Assert: Verificar resultados
		assertNotNull(result, "El cliente retornado no debe ser null");
		assertEquals(id, result.getId(), "El ID debe coincidir");
		assertEquals("Juan", result.getNombre(), "El nombre debe coincidir");
		verify(clienteRepository, times(1)).findById(id);
	}
}