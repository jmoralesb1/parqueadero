package com.ejemplo.parqueadero.repository;

import com.ejemplo.parqueadero.model.Registros;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registros, Long>{
    Optional<Registros> findByVehiculoPlacaAndFechaSalidaIsNull(String placa);
}
