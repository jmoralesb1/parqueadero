package com.ejemplo.parqueadero.repository;

import com.ejemplo.parqueadero.model.Vehiculo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    Optional<Vehiculo> findByPlaca(String placa);
    
}
