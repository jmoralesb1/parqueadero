package com.ejemplo.parqueadero.dto;

public class RegistroEntradaDTO {
    private String placa; // Usamos la placa
    private Long celdaId;

    // Constructor
    public RegistroEntradaDTO(String placa, Long celdaId) {
        this.placa = placa;
        this.celdaId = celdaId;
    }

    // getters y setters
    
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public Long getCeldaId() {
        return celdaId;
    }
    public void setCeldaId(Long celdaId) {
        this.celdaId = celdaId;
    }

    
}
