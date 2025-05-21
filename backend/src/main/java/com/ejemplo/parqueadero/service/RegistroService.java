package com.ejemplo.parqueadero.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ejemplo.parqueadero.model.Celda;
import com.ejemplo.parqueadero.model.Registros;
import com.ejemplo.parqueadero.model.TipoCelda;
import com.ejemplo.parqueadero.model.Vehiculo;
import com.ejemplo.parqueadero.repository.CeldaRepository;
import com.ejemplo.parqueadero.repository.RegistroRepository;
import com.ejemplo.parqueadero.model.EstadoCelda;

@Service
public class RegistroService {
    
    private final RegistroRepository registroRepository;
    private final CeldaRepository celdaRepository;

    public RegistroService(RegistroRepository registroRepository, CeldaRepository celdaRepository){
        this.registroRepository = registroRepository;
        this.celdaRepository = celdaRepository;
    }

    public Registros registrarEntrada(Vehiculo vehiculo) {
        String tipoNormalizado = vehiculo.getTipo().toUpperCase().trim();
        System.out.println("Tipo de vehículo recibido: " + tipoNormalizado);
        TipoCelda tipoCelda = TipoCelda.valueOf(tipoNormalizado); // Convierte el String a Enum
        Celda celdaDisponible = buscarCeldaDisponible(tipoCelda);


        if (celdaDisponible != null) {
            Registros registro = new Registros();
            registro.setVehiculo(vehiculo);
            registro.setCelda(celdaDisponible);
            registro.setFechaEntrada(LocalDateTime.now());
            
            celdaDisponible.setEstado(EstadoCelda.OCUPADO);
            celdaRepository.save(celdaDisponible);

            return registroRepository.save(registro);
        } else {
            throw new RuntimeException("No hay celdas disponibles");
        }
    }

    public Registros registrarSalida(Long registroId) {
        // Lógica para registrar la salida del vehículo
        Registros registro = registroRepository.findById(registroId)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        Celda celda = registro.getCelda();
        celda.setEstado(EstadoCelda.DISPONIBLE);
        celdaRepository.save(celda);

        registro.setFechaSalida(LocalDateTime.now());
        return registroRepository.save(registro);
    }

    // Ejemplo en RegistroService.java
    public Celda buscarCeldaDisponible(TipoCelda tipoVehiculo) {
    EstadoCelda estadoLibre = EstadoCelda.DISPONIBLE;
    return celdaRepository.findFirstByTipoAndEstado(tipoVehiculo, estadoLibre)
        .orElseThrow(() -> new RuntimeException("No hay celdas disponibles para este tipo"));
}
}
