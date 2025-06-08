package com.ejemplo.parqueadero.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "celdas")
public class Celda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoCelda estado;

    @Enumerated(EnumType.STRING)
    private TipoCelda tipo;

    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    public Celda() {}
    public Celda(EstadoCelda estado, TipoCelda tipo) {
        this.estado = estado;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public EstadoCelda getEstado() { return estado; }
    public TipoCelda getTipo() { return tipo; }
    public Vehiculo getVehiculo() { return vehiculo; }
    
    public void setId(Long id) { this.id = id; }
    public void setEstado(EstadoCelda estado) { this.estado = estado; }
    public void setTipo(TipoCelda tipo) { this.tipo = tipo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
}


