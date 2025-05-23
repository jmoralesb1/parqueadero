package com.ejemplo.parqueadero.controller;

import com.ejemplo.parqueadero.model.Celda;
import com.ejemplo.parqueadero.dto.CeldaDTO;
import com.ejemplo.parqueadero.dto.VehiculoDTO;
import com.ejemplo.parqueadero.service.CeldaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/celdas")
public class CeldaController {

    @Autowired
    private CeldaService celdaService;

    // Endpoint para retornar todas las celdas en formato DTO (con información del vehículo si está ocupada)
    @GetMapping("/dto")
    public List<CeldaDTO> obtenerCeldasDTO() {
        List<Celda> celdas = celdaService.obtenerTodas();
        return celdas.stream().map(celda -> {
            VehiculoDTO vehiculoDTO = null;
            if (celda.getVehiculo() != null) {
                vehiculoDTO = new VehiculoDTO(
                        celda.getVehiculo().getPlaca(),
                        celda.getVehiculo().getMarca()
                );
            }
            return new CeldaDTO(
                    celda.getId(),
                    celda.getEstado().toString(),
                    celda.getTipo().toString(),
                    vehiculoDTO
            );
        }).collect(Collectors.toList());
    }

    // Puedes dejar tus endpoints antiguos si el frontend los usa
    @GetMapping
    public List<Celda> listarCeldas() {
        return celdaService.obtenerTodas();
    }

    @PostMapping
    public Celda registrarCelda(@RequestBody Celda celda) {
        return celdaService.guardarCelda(celda);
    }

    @GetMapping("/disponibles")
    public List<Celda> obtenerCeldasDisponibles() {
        // Implementa este método en el service si lo necesitas
        return celdaService.obtenerPorEstado("DISPONIBLE");
    }

    @GetMapping("/ocupadas")
    public List<Celda> obtenerCeldasOcupadas() {
        // Implementa este método en el service si lo necesitas
        return celdaService.obtenerPorEstado("OCUPADO");
    }
}

