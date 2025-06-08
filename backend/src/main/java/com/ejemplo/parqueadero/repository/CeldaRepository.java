package com.ejemplo.parqueadero.repository;

import com.ejemplo.parqueadero.model.Celda;
import com.ejemplo.parqueadero.model.EstadoCelda;
import com.ejemplo.parqueadero.model.TipoCelda;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CeldaRepository extends JpaRepository<Celda, Long> {

    
    Celda findFirstByEstado(EstadoCelda estado);
    Optional<Celda> findFirstByTipoAndEstado(TipoCelda tipo, EstadoCelda estado);
    List<Celda> findByEstado(String estado);
    Optional<Celda> findFirstByEstadoAndTipo(EstadoCelda estado, TipoCelda tipo);

    // Métodos para el panel resumen
    long countByEstado(EstadoCelda estado);
}

