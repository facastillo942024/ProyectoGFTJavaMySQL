package com.gftfabiancastillo.demoFabianCastillo.service;

import com.gftfabiancastillo.demoFabianCastillo.model.Fondo;
import com.gftfabiancastillo.demoFabianCastillo.model.Transaccion;
import com.gftfabiancastillo.demoFabianCastillo.repository.ClienteRepository;
import com.gftfabiancastillo.demoFabianCastillo.repository.FondoRepository;
import com.gftfabiancastillo.demoFabianCastillo.repository.TransaccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FondoServiceTest {
    private FondoRepository fondoRepo;
    private TransaccionRepository transaccionRepo;
    private ClienteRepository clienteRepo;
    private FondoService fondoService;

    @BeforeEach
    public void setUp() {
        fondoRepo = mock(FondoRepository.class);
        transaccionRepo = mock(TransaccionRepository.class);
        clienteRepo = mock(ClienteRepository.class);
        fondoService = new FondoService(fondoRepo, transaccionRepo, clienteRepo);
    }

    @Test
    public void suscripcionExitosaTest() {
        Fondo fondo = new Fondo(1L, "FPV_BTG", 100000, "FPV");
        when(fondoRepo.findById("1")).thenReturn(Optional.of(fondo));

        String resultado = fondoService.suscribirAFondo("1");

        assertTrue(resultado.contains("Suscripción exitosa"));
        verify(transaccionRepo, times(1)).save(any(Transaccion.class));
    }

    @Test
    public void suscripcionSaldoInsuficienteTest() {
        Fondo fondo = new Fondo(1L, "FDO-ACCIONES", 600000, "FIC");
        when(fondoRepo.findById("1")).thenReturn(Optional.of(fondo));

        String resultado = fondoService.suscribirAFondo("1");

        assertEquals("No tiene saldo disponible para vincularse al fondo FDO-ACCIONES", resultado);
        verify(transaccionRepo, never()).save(any());
    }

    @Test
    public void fondoNoExisteTest() {
        when(fondoRepo.findById("999")).thenReturn(Optional.empty());

        String resultado = fondoService.suscribirAFondo("999");

        assertEquals("Fondo no encontrado", resultado);
        verify(transaccionRepo, never()).save(any());
    }
}