package com.ejemplo.parqueadero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.parqueadero.model.Celda;
import com.ejemplo.parqueadero.repository.CeldaRepository;

@RestController
@RequestMapping("/celdas")
public class CeldaController {

    @Autowired
    private CeldaRepository celdaRepository;

    @GetMapping
    public List<Celda> listarCeldas() {
        return celdaRepository.findAll();
    }

    @PostMapping
    public Celda registrarCelda(@RequestBody Celda celda) {
        return celdaRepository.save(celda);
    }
}