package com.ejemplo.parqueadero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ejemplo.parqueadero.model.Registros;
import com.ejemplo.parqueadero.model.Vehiculo;
import com.ejemplo.parqueadero.repository.RegistroRepository;
import com.ejemplo.parqueadero.repository.VehiculoRepository;
import com.ejemplo.parqueadero.service.RegistroService;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private RegistroService registroService;

    @Autowired
    private RegistroRepository registroRepository;

    public RegistroController(RegistroService registroService, VehiculoRepository vehiculoRepository, RegistroRepository registroRepository) {
        this.registroService = registroService;
        this.vehiculoRepository = vehiculoRepository;
        this.registroRepository = registroRepository;
    }
    @PostMapping("/entrada/placa/{placa}")
    public Registros registrarEntradaPorPlaca(@PathVariable String placa) {
    Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    return registroService.registrarEntrada(vehiculo);
}

    @PutMapping("/salida/placa/{placa}")
    public Registros registrarSalidaPorPlaca(@PathVariable String placa) {
    Registros registro = registroRepository.findByVehiculoPlacaAndFechaSalidaIsNull(placa)
        .orElseThrow(() -> new RuntimeException("Registro de entrada no encontrado para esta placa"));
    return registroService.registrarSalida(registro.getId());
}

    @GetMapping
    public List<Registros> listarRegistros() {
        return registroRepository.findAll();
}
}