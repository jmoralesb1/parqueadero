package com.ejemplo.parqueadero.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double monto;
    private LocalDateTime fechaPago;

    @Column(name = "metodo_pago")
    private String metodoPago;

    //usuario que registra/cobra el pago
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    //referencia de pago (puede ser de una transacción bancaria, número de recibo, etc)
    @Column(name = "referencia")
    private String referencia;

    //observaciones adicionales al pago
    @Column(name = "observaciones")
    private String observaciones;

    //estado del pago (ejemplo: Pagado, Parcial, Anulado)
    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "registro_id")
    private Registros registro;

    public Pagos() {}

    public Pagos(Double monto, LocalDateTime fechaPago, Registros registro) {
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.registro = registro;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Registros getRegistro() { return registro; }
    public void setRegistro(Registros registro) { this.registro = registro; }
}

