package com.example.ERP;

import com.example.ERP.application.services.ClienteService;
import com.example.ERP.application.services.HistoriaClinicaService;
import com.example.ERP.domain.entities.Cliente;
import com.example.ERP.domain.entities.HistoriaClinica;
import com.example.ERP.infrastructure.repositories.HistoriaClinicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoriaClinicaServiceTests {

    @Mock
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private HistoriaClinicaService historiaClinicaService;

    private HistoriaClinica historiaClinica;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        // Inicializar un cliente de prueba
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCedula("1234567890");
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");

        // Inicializar una historia clínica de prueba
        historiaClinica = new HistoriaClinica();
        historiaClinica.setId(1L);
        historiaClinica.setCliente(cliente);
        historiaClinica.setDescripcion("Consulta inicial");
        historiaClinica.setFechaConsulta(LocalDate.of(2025, 5, 11));
    }

    @Test
    void testCrearHistoria_ValidHistoria_Success() {
        // Arrange: Configurar el mock
        when(clienteService.obtenerPorId(1L)).thenReturn(cliente);
        when(historiaClinicaRepository.save(any(HistoriaClinica.class))).thenReturn(historiaClinica);

        // Act: Invocar el método
        HistoriaClinica result = historiaClinicaService.crearHistoria(historiaClinica);

        // Assert: Verificar resultados
        assertNotNull(result, "La historia clínica retornada no debe ser null");
        assertEquals(1L, result.getId(), "El ID debe coincidir");
        assertEquals("Consulta inicial", result.getDescripcion(), "La descripción debe coincidir");
        assertEquals(LocalDate.of(2025, 5, 11), result.getFechaConsulta(), "La fecha debe coincidir");
        assertEquals(cliente, result.getCliente(), "El cliente debe coincidir");
        verify(clienteService, times(1)).obtenerPorId(1L);
        verify(historiaClinicaRepository, times(1)).save(any(HistoriaClinica.class));
    }

    @Test
    void testObtenerPorId_ExistingId_ReturnsHistoria() {
        // Arrange: Configurar datos de prueba
        Long id = 1L;
        when(historiaClinicaRepository.findById(id)).thenReturn(Optional.of(historiaClinica));

        // Act: Invocar el método
        HistoriaClinica result = historiaClinicaService.obtenerPorId(id);

        // Assert: Verificar resultados
        assertNotNull(result, "La historia clínica retornada no debe ser null");
        assertEquals(id, result.getId(), "El ID debe coincidir");
        assertEquals("Consulta inicial", result.getDescripcion(), "La descripción debe coincidir");
        assertEquals(cliente, result.getCliente(), "El cliente debe coincidir");
        verify(historiaClinicaRepository, times(1)).findById(id);
    }
}