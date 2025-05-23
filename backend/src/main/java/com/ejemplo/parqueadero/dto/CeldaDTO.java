package com.ejemplo.parqueadero.dto;

public class CeldaDTO {
    private Long id;
    private String estado;
    private String tipo;
    private VehiculoDTO vehiculo; // Puede ser null si está libre

    // Constructor
    public CeldaDTO(Long id, String estado, String tipo, VehiculoDTO vehiculo) {
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
        this.vehiculo = vehiculo;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }
    
}
