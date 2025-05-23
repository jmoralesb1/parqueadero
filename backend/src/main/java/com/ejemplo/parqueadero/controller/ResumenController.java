package com.ejemplo.parqueadero.controller;

import com.ejemplo.parqueadero.dto.ResumenDTO;
import com.ejemplo.parqueadero.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resumen")
public class ResumenController {

    @Autowired
    private RegistroService registroService;

    @GetMapping
    public ResumenDTO getResumen() {
        ResumenDTO resumen = new ResumenDTO();
        resumen.setTotalEntradasHoy(registroService.totalEntradasHoy());
        resumen.setTotalSalidasHoy(registroService.totalSalidasHoy());
        resumen.setCeldasOcupadas(registroService.celdasOcupadas());
        resumen.setCeldasDisponibles(registroService.celdasDisponibles());
        resumen.setVehiculosMasFrecuentes(registroService.vehiculosMasFrecuentes(3)); // Top 3
        return resumen;
    }
}
