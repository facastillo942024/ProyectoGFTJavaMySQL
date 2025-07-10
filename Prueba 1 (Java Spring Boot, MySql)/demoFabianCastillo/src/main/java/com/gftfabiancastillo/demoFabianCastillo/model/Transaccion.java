package com.gftfabiancastillo.demoFabianCastillo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fondoId;
    private String tipo; // Apertura o Cancelación
    private LocalDateTime fecha;
    private int monto;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // FK en la tabla transaccion
    @JsonBackReference //ANOTACION PARA QUE EL CLIENTE NO SE REPITA EN CADA FINDTOALL() (HISTORIAL)
    private Cliente cliente;


    public Transaccion() {}
    public Transaccion(String fondoId, String tipo, int monto, Cliente cliente) {
        this.fondoId = fondoId;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
        this.monto = monto;
        this.cliente = cliente;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFondoId() {
        return fondoId;
    }

    public void setFondoId(String fondoId) {
        this.fondoId = fondoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
