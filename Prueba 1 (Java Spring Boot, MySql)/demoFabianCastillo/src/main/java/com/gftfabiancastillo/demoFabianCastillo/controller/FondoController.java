package com.gftfabiancastillo.demoFabianCastillo.controller;

import com.gftfabiancastillo.demoFabianCastillo.model.Transaccion;
import com.gftfabiancastillo.demoFabianCastillo.service.FondoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fondos")
public class FondoController {

    @Autowired
    private final FondoService servicio;

    public FondoController(FondoService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/suscribir/{id}")
    @Operation(
            summary = "Suscribir a cliente ID 1 a Fondo (Apertura)",
            description = "Suscribir a cliente ID 1 a Fondo (Apertura) en base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Fondo suscrito correctamente.")
            }
    )
    public String suscribir(@PathVariable String id) {
        return servicio.suscribirAFondo(id);
    }

    @PostMapping("/cancelar/{id}")
    @Operation(
            summary = "Cancelar suscripción a cliente ID 1 a Fondo (Cancelación)",
            description = "Cancelar suscripción a cliente ID 1 a Fondo (Cancelación) en base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Suscripcion a fondo cancelada correctamente.")
            }
    )
    public String cancelar(@PathVariable String id) {
        return servicio.cancelarSuscripcion(id);
    }

    @GetMapping("/historial")
    @Operation(
            summary = "Ver historial de transacciones cliente ID 1",
            description = "Ver historial de transacciones cliente ID 1 en base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorna historial correctamente.")
            }
    )
    public List<Transaccion> historial() {
        return servicio.obtenerHistorial();
    }

    @GetMapping("/saldo")
    @Operation(
            summary = "Ver saldo actual del cliente ID 1",
            description = "Ver saldo actual del cliente ID 1 en base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorna saldo actual de cliente correctamente.")
            }
    )
    public int saldo() {
        return servicio.getSaldoCliente();
    }
}