package com.ejemplo.parqueadero.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "registros")

public class Registros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo:id")
    private Vehiculo vehiculo;

    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    @ManyToOne
    @JoinColumn(name = "celda_id")
    private Celda celda;

    public Registros() {}

    public Registros(Vehiculo vehiculo, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Celda celda) {
        this.vehiculo = vehiculo;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.celda = celda;
    }

    public Long getId() { return id; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public LocalDateTime getFechaEntrada() { return fechaEntrada; }
    public LocalDateTime getFechaSalida() {return fechaSalida; }
    public Celda getCelda () {return celda;}

    public void setId(Long id) {this.id = id;}
    public void setVehiculo(Vehiculo vehiculo) {this.vehiculo = vehiculo;}  
    public void setFechaEntrada(LocalDateTime fechaEntrada) {this.fechaEntrada = fechaEntrada;}     
    public void setFechaSalida(LocalDateTime fechaSalida) {this.fechaSalida = fechaSalida;}
    public void setCelda(Celda celda) {this.celda = celda; }    
}
