package com.ejemplo.parqueadero.dto;

public class VehiculoDTO {
    private String placa;
    private String marca;

    // Constructor
    public VehiculoDTO(String placa, String marca) {
        this.placa = placa;
        this.marca = marca;
    }

    // Getters y setters
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
}
