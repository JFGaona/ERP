package com.example.ERP;

import com.example.ERP.application.services.MarcoDeLenteService;
import com.example.ERP.domain.entities.MarcoDeLente;
import com.example.ERP.infrastructure.repositories.MarcoDeLenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarcoDeLenteServiceTests {

    @Mock
    private MarcoDeLenteRepository marcoDeLenteRepository;

    @InjectMocks
    private MarcoDeLenteService marcoDeLenteService;

    private MarcoDeLente marcoDeLente;

    @BeforeEach
    void setUp() {
        // Inicializar un marco de lente de prueba
        marcoDeLente = new MarcoDeLente();
        marcoDeLente.setId(1L);
        marcoDeLente.setModelo("Ray-Ban 123");
        marcoDeLente.setMarca("Ray-Ban");
        marcoDeLente.setDescripcion("Marco de acetato negro");
        marcoDeLente.setCantidadDisponible(10);
        marcoDeLente.setPrecio(BigDecimal.valueOf(100.0));
        marcoDeLente.setEstado("DISPONIBLE");
    }

    @Test
    void testCrearMarco_ValidMarco_Success() {
        // Arrange: Configurar el mock
        when(marcoDeLenteRepository.save(any(MarcoDeLente.class))).thenReturn(marcoDeLente);

        // Act: Invocar el método
        MarcoDeLente result = marcoDeLenteService.crearMarco(marcoDeLente);

        // Assert: Verificar resultados
        assertNotNull(result, "El marco de lente retornado no debe ser null");
        assertEquals(1L, result.getId(), "El ID debe coincidir");
        assertEquals("Ray-Ban 123", result.getModelo(), "El modelo debe coincidir");
        assertEquals("Ray-Ban", result.getMarca(), "La marca debe coincidir");
        assertEquals(10, result.getCantidadDisponible(), "La cantidad disponible debe coincidir");
        assertEquals(BigDecimal.valueOf(100.0), result.getPrecio(), "El precio debe coincidir");
        assertEquals("DISPONIBLE", result.getEstado(), "El estado debe coincidir");
        verify(marcoDeLenteRepository, times(1)).save(any(MarcoDeLente.class));
    }

    @Test
    void testObtenerPorId_ExistingId_ReturnsMarco() {
        // Arrange: Configurar datos de prueba
        Long id = 1L;
        when(marcoDeLenteRepository.findById(id)).thenReturn(Optional.of(marcoDeLente));

        // Act: Invocar el método
        MarcoDeLente result = marcoDeLenteService.obtenerPorId(id);

        // Assert: Verificar resultados
        assertNotNull(result, "El marco de lente retornado no debe ser null");
        assertEquals(id, result.getId(), "El ID debe coincidir");
        assertEquals("Ray-Ban 123", result.getModelo(), "El modelo debe coincidir");
        assertEquals("DISPONIBLE", result.getEstado(), "El estado debe coincidir");
        verify(marcoDeLenteRepository, times(1)).findById(id);
    }
}