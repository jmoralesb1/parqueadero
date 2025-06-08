package com.ejemplo.parqueadero.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.parqueadero.model.Pagos;



public interface PagosRepository extends JpaRepository<Pagos, Long> {
    List<Pagos> findByRegistroVehiculoPlaca(String placa);
}
