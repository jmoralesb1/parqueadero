package com.ejemplo.parqueadero.dto;

import java.util.Map;

public class ResumenDTO {
    private long totalEntradasHoy;
    private long totalSalidasHoy;
    private long celdasOcupadas;
    private long celdasDisponibles;
    private Map<String, Long> vehiculosMasFrecuentes; // placa -> cantidad

    // Getters y setters

    public long getTotalEntradasHoy() {
        return totalEntradasHoy;
    }

    public void setTotalEntradasHoy(long totalEntradasHoy) {
        this.totalEntradasHoy = totalEntradasHoy;
    }

    public long getTotalSalidasHoy() {
        return totalSalidasHoy;
    }

    public void setTotalSalidasHoy(long totalSalidasHoy) {
        this.totalSalidasHoy = totalSalidasHoy;
    }

    public long getCeldasOcupadas() {
        return celdasOcupadas;
    }

    public void setCeldasOcupadas(long celdasOcupadas) {
        this.celdasOcupadas = celdasOcupadas;
    }

    public long getCeldasDisponibles() {
        return celdasDisponibles;
    }

    public void setCeldasDisponibles(long celdasDisponibles) {
        this.celdasDisponibles = celdasDisponibles;
    }

    public Map<String, Long> getVehiculosMasFrecuentes() {
        return vehiculosMasFrecuentes;
    }

    public void setVehiculosMasFrecuentes(Map<String, Long> vehiculosMasFrecuentes) {
        this.vehiculosMasFrecuentes = vehiculosMasFrecuentes;
    }
}
