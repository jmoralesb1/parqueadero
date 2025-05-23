package com.ejemplo.parqueadero.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejemplo.parqueadero.model.Celda;
import com.ejemplo.parqueadero.model.Registros;
import com.ejemplo.parqueadero.model.TipoCelda;
import com.ejemplo.parqueadero.model.Vehiculo;
import com.ejemplo.parqueadero.repository.CeldaRepository;
import com.ejemplo.parqueadero.repository.RegistroRepository;
import com.ejemplo.parqueadero.repository.VehiculoRepository;
import com.ejemplo.parqueadero.model.EstadoCelda;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;
    @Autowired
    private CeldaRepository celdaRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    /*public Registros registrarEntrada(Long vehiculoId, Long celdaId) {
        // 1. Buscar la celda y el vehículo
        Celda celda = celdaRepository.findById(celdaId)
                        .orElseThrow(() -> new RuntimeException("Celda no encontrada"));
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        // 2. Verificar que la celda esté disponible
        if (!celda.getEstado().equals(EstadoCelda.DISPONIBLE)) {
            throw new RuntimeException("La celda no está disponible");
        }

        // 3. Crear registro de entrada
        Registros registro = new Registros();
        registro.setCelda(celda);
        registro.setVehiculo(vehiculo);
        registro.setFechaEntrada(LocalDateTime.now());
        registro.setFechaSalida(null);

        registroRepository.save(registro);

        // 4. Actualizar celda: asociar vehículo y poner OCUPADO
        celda.setVehiculo(vehiculo);
        celda.setEstado(EstadoCelda.OCUPADO);
        celdaRepository.save(celda);

        return registro;
    }*/

        public Registros registrarEntradaPorPlaca(String placa) {
        // 1. Buscar el vehículo
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
            .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        // 2. Buscar una celda disponible del tipo adecuado
        TipoCelda tipoCelda = TipoCelda.valueOf(vehiculo.getTipo().toUpperCase()); // Convierte String a Enum
        Celda celdaDisponible = celdaRepository.findFirstByEstadoAndTipo(
            EstadoCelda.DISPONIBLE,
            tipoCelda
        ).orElseThrow(() -> new RuntimeException("No hay celdas disponibles para este tipo de vehículo"));


        // 3. Crear el registro de entrada
        Registros registro = new Registros();
        registro.setCelda(celdaDisponible);
        registro.setVehiculo(vehiculo);
        registro.setFechaEntrada(LocalDateTime.now());
        registro.setFechaSalida(null);
        registroRepository.save(registro);

        // 4. Actualiza la celda
        celdaDisponible.setVehiculo(vehiculo);
        celdaDisponible.setEstado(EstadoCelda.OCUPADO);
        celdaRepository.save(celdaDisponible);

        return registro;
    }




    public Registros registrarSalida(Long id) {
    // 1. Buscar el registro de entrada por id
    Registros registro = registroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

    // 2. Validar que no tenga ya fecha de salida
    if (registro.getFechaSalida() != null) {
        throw new RuntimeException("Este registro ya tiene fecha de salida");
    }

    // 3. Poner fecha de salida actual
    registro.setFechaSalida(LocalDateTime.now());

    // 4. Liberar la celda (estado DISPONIBLE y sin vehículo)
    Celda celda = registro.getCelda();
    celda.setEstado(EstadoCelda.DISPONIBLE);
    celda.setVehiculo(null);
    celdaRepository.save(celda);

    // 5. Guardar registro
    return registroRepository.save(registro);
    }

        // Total de entradas de hoy
    public long totalEntradasHoy() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);
        return registroRepository.countByFechaEntradaBetween(start, end);
    }

    // Total de salidas de hoy
    public long totalSalidasHoy() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);
        return registroRepository.countByFechaSalidaBetween(start, end);
    }

    // Total de celdas ocupadas
    public long celdasOcupadas() {
        return celdaRepository.countByEstado(EstadoCelda.OCUPADO);
    }

    // Total de celdas disponibles
    public long celdasDisponibles() {
        return celdaRepository.countByEstado(EstadoCelda.DISPONIBLE);
    }

    // Vehículos más frecuentes (Top 3 por cantidad de entradas)
    public Map<String, Long> vehiculosMasFrecuentes(int top) {
        return registroRepository.findAll().stream()
            .collect(Collectors.groupingBy(r -> r.getVehiculo().getPlaca(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(top)
            .collect(Collectors.toMap(
                Map.Entry::getKey, 
                Map.Entry::getValue,
                (e1, e2) -> e1,
                java.util.LinkedHashMap::new
            ));
    }
}
