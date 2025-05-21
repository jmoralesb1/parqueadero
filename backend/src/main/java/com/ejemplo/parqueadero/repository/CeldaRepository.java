package com.ejemplo.parqueadero.repository;

import com.ejemplo.parqueadero.model.Celda;
import com.ejemplo.parqueadero.model.EstadoCelda;
import com.ejemplo.parqueadero.model.TipoCelda;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CeldaRepository extends JpaRepository<Celda, Long> {

    Celda findFirstByEstado(EstadoCelda estado);
    Optional<Celda> findFirstByTipoAndEstado(TipoCelda tipo, EstadoCelda estado);
    
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar celdas por estado
    // Optional<Celda> findByEstado(String estado);
 
} 
