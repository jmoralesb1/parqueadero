package com.ejemplo.parqueadero.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ejemplo.parqueadero.model.Registros;
import com.ejemplo.parqueadero.repository.RegistroRepository;
import com.ejemplo.parqueadero.repository.VehiculoRepository;
import com.ejemplo.parqueadero.service.RegistroService;

@RestController
@RequestMapping("/registro")
public class RegistroController {


    @Autowired
    private RegistroService registroService;

    @Autowired
    private RegistroRepository registroRepository;

    public RegistroController(RegistroService registroService, VehiculoRepository vehiculoRepository, RegistroRepository registroRepository) {
        this.registroService = registroService;
        this.registroRepository = registroRepository;
    }


    // Importa DTO sencillo para recibir solo placa, o usa un String directo
    @PostMapping("/entrada/placa/{placa}")
    public ResponseEntity<?> registrarEntradaPorPlaca(@PathVariable String placa) {
        try {
            Registros registro = registroService.registrarEntradaPorPlaca(placa);
            return ResponseEntity.ok(registro);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    // Registrar salida por placa
    @PutMapping("/salida/placa/{placa}")
    public Registros registrarSalidaPorPlaca(@PathVariable String placa) {
        Registros registro = registroRepository.findByVehiculoPlacaAndFechaSalidaIsNull(placa)
            .orElseThrow(() -> new RuntimeException("Registro de entrada no encontrado para esta placa"));
        return registroService.registrarSalida(registro.getId());
    }

    // Listar todos los registros
    @GetMapping
    public List<Registros> listarRegistros() {
        return registroRepository.findAll();
    }

    @GetMapping("/placa/{placa}")
    public List<Registros> historialPorPlaca(@PathVariable String placa) {
        return registroRepository.findByVehiculoPlaca(placa);
    }

    @GetMapping("/resumen")
    public Map<String, Object> obtenerResumen() {
    Map<String, Object> resumen = new java.util.HashMap<>();
    resumen.put("entradasHoy", registroService.totalEntradasHoy());
    resumen.put("salidasHoy", registroService.totalSalidasHoy());
    resumen.put("celdasOcupadas", registroService.celdasOcupadas());
    resumen.put("celdasDisponibles", registroService.celdasDisponibles());
    resumen.put("vehiculosMasFrecuentes", registroService.vehiculosMasFrecuentes(3));
    return resumen;
}


}
