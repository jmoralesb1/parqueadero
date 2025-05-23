package com.ejemplo.parqueadero.repository;

import com.ejemplo.parqueadero.model.Registros;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistroRepository extends JpaRepository<Registros, Long>{
    Optional<Registros> findByVehiculoPlacaAndFechaSalidaIsNull(String placa);
    List<Registros> findByVehiculoPlaca(String placa);

    @Query("SELECT COUNT(r) FROM Registros r WHERE DATE(r.fechaEntrada) = CURRENT_DATE")
    Long countEntradasHoy();

    @Query("SELECT COUNT(r) FROM Registros r WHERE DATE(r.fechaSalida) = CURRENT_DATE")
    Long countSalidasHoy();

    @Query("SELECT r.vehiculo.placa, COUNT(r) as total FROM Registros r GROUP BY r.vehiculo.placa ORDER BY total DESC")
    List<Object[]> findVehiculosFrecuentes(Pageable pageable);

    long countByFechaEntradaBetween(LocalDateTime inicio, LocalDateTime fin);
    long countByFechaSalidaBetween(LocalDateTime inicio, LocalDateTime fin);
    long countByFechaEntrada(LocalDateTime fechaEntrada); // opcional
    long countByFechaSalida(LocalDateTime fechaSalida);   // opcional


}

