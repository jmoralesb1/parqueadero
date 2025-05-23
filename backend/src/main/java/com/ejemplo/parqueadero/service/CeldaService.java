package com.ejemplo.parqueadero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejemplo.parqueadero.model.Celda;
import com.ejemplo.parqueadero.repository.CeldaRepository;

@Service
public class CeldaService {
    @Autowired
    private CeldaRepository celdaRepository;

    public List<Celda> obtenerTodas() {
        return celdaRepository.findAll();
    }

    public List<Celda> obtenerCeldasPorEstado(String estado) {
        return celdaRepository.findByEstado(estado);
    }

    public Celda registrarCelda(Celda celda) {
        return celdaRepository.save(celda);
    }

    public Celda guardarCelda(Celda celda) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarCelda'");
    }

    public List<Celda> obtenerPorEstado(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorEstado'");
    }
}
