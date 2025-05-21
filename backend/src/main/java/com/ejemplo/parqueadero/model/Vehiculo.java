package com.ejemplo.parqueadero.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String tipo; // Nuevo campo agregado

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Vehiculo() {}

    // Getters
    public Long getId() { return id; }
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
    public String getTipo() { return tipo; }
    public Usuario getUsuario() { return usuario; }

    // Setters
    public void setPlaca(String placa) { this.placa = placa; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setColor(String color) { this.color = color; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}

enum EstadoVehiculo {
    DISPONIBLE,
    OCUPADO
}