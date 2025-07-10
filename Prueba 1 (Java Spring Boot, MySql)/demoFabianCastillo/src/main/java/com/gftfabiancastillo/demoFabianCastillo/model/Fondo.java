package com.gftfabiancastillo.demoFabianCastillo.model;

import jakarta.persistence.*;

@Entity
public class Fondo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int montoMinimo;
    private String categoria;

    public Fondo() {}
    public Fondo(Long id, String nombre, int montoMinimo, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.montoMinimo = montoMinimo;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(int montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
