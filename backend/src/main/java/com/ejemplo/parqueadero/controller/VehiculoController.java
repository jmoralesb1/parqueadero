package com.ejemplo.parqueadero.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.parqueadero.model.Usuario;
import com.ejemplo.parqueadero.model.Vehiculo;
import com.ejemplo.parqueadero.repository.VehiculoRepository;
import com.ejemplo.parqueadero.repository.UsuarioRepository;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    private final VehiculoRepository vehiculoRepository;
    private final UsuarioRepository usuarioRepository; 

    //constructor para recibir ambos repositorios
    public VehiculoController(VehiculoRepository vehiculoRepository, UsuarioRepository usuarioRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Endpoint para crear un nuevo vehículo
    @PostMapping
    public Vehiculo crearVehiculo(@RequestBody Vehiculo vehiculo) {
        System.out.println("Tipo recibido: " + vehiculo.getTipo()); // <-- Agrega esto
        if (vehiculo.getUsuario() != null && vehiculo.getUsuario().getId() != null) {
            Usuario usuarioCompleto = usuarioRepository.findById(vehiculo.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            vehiculo.setUsuario(usuarioCompleto);
        }
        return vehiculoRepository.save(vehiculo);
    }

    
    @PostMapping("/registrar-con-cedula")
    public Vehiculo crearVehiculoConCedula(@RequestBody Vehiculo vehiculo, @RequestParam Long cedula) {
        Usuario usuario = usuarioRepository.findByCedula(cedula)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con esa cédula"));
        vehiculo.setUsuario(usuario);
        return vehiculoRepository.save(vehiculo);
}

    @GetMapping
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
}

}