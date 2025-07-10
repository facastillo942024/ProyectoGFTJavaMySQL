package com.gftfabiancastillo.demoFabianCastillo.service;

import com.gftfabiancastillo.demoFabianCastillo.model.Cliente;
import com.gftfabiancastillo.demoFabianCastillo.model.Fondo;
import com.gftfabiancastillo.demoFabianCastillo.model.Transaccion;
import com.gftfabiancastillo.demoFabianCastillo.repository.ClienteRepository;
import com.gftfabiancastillo.demoFabianCastillo.repository.FondoRepository;
import com.gftfabiancastillo.demoFabianCastillo.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FondoService {

    @Autowired
    private final FondoRepository fondoRepo;
    @Autowired
    private final TransaccionRepository transaccionRepo;
    @Autowired
    private final ClienteRepository clienteRepo;
    private final Cliente clientePrimero;

    private int saldoCliente = 0;

    public FondoService(FondoRepository fondoRepo, TransaccionRepository transaccionRepo, ClienteRepository clienteRepo) {
        this.fondoRepo = fondoRepo;
        this.transaccionRepo = transaccionRepo;
        this.clienteRepo = clienteRepo;
        //TRAER SALDO DE CLIENTE 1 (PRUEBA)
        clientePrimero = obtenerPorIdCliente(1L);
        this.saldoCliente = clientePrimero.getSaldo();
        //*********************************
    }

    public String suscribirAFondo(String fondoId) {
        Optional<Fondo> optionalFondo = fondoRepo.findById(fondoId);
        if (optionalFondo.isEmpty()) return "Fondo no encontrado";

        Fondo fondo = optionalFondo.get();
        if (saldoCliente < fondo.getMontoMinimo()) {
            return "No tiene saldo disponible para vincularse al fondo " + fondo.getNombre();
        }

        saldoCliente -= fondo.getMontoMinimo();
        clientePrimero.setSaldo(saldoCliente);//ASIGNAR NUEVO SALDO A CLIENTE
        clienteRepo.save(clientePrimero);//GUARDAR CAMBIOS CLIENTE
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        Transaccion tx = new Transaccion(fondoId, "Apertura", fondo.getMontoMinimo(), cliente);
        transaccionRepo.save(tx);

        return "Suscripción exitosa al fondo " + fondo.getNombre();
    }

    public String cancelarSuscripcion(String fondoId) {
        Optional<Fondo> optionalFondo = fondoRepo.findById(fondoId);
        if (optionalFondo.isEmpty()) return "Fondo no encontrado";

        Fondo fondo = optionalFondo.get();
        saldoCliente += fondo.getMontoMinimo();
        clientePrimero.setSaldo(saldoCliente);//ASIGNAR NUEVO SALDO A CLIENTE
        clienteRepo.save(clientePrimero);//GUARDAR CAMBIOS CLIENTE
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        transaccionRepo.save(new Transaccion(fondoId, "Cancelación", fondo.getMontoMinimo(), cliente));
        return "Cancelación exitosa y fondos retornados.";
    }

    public List<Transaccion> obtenerHistorial() {
        return transaccionRepo.findAll();
    }

    public int getSaldoCliente() {
        return saldoCliente;
    }

    public Cliente obtenerPorIdCliente(Long id) {
        return clienteRepo.findById(id.toString())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}