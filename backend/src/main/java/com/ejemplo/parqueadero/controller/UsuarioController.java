package com.ejemplo.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ejemplo.parqueadero.model.Usuario;
import com.ejemplo.parqueadero.repository.UsuarioRepository;
import com.ejemplo.parqueadero.service.UsuarioService;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository; // <-- Agrega esta línea

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.findUsuarioById(id);
    }

    @GetMapping("/cedula/{cedula}")
    public Usuario obtenerUsuarioPorCedula(@PathVariable int cedula) {
        return usuarioService.findUsuarioByCedula(cedula);
    }

    //@PutMapping("/actualizar/{id}")
    //public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
    //return usuarioService.updateUsuario(id, usuarioActualizado);


    //Temporal
    @DeleteMapping("/actualizar/{id}")
    public void eliminarUsuarioPorId(@PathVariable Long id) {
    usuarioService.deleteUsuarioById(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        usuario.setContrasena(usuarioActualizado.getContrasena());
        usuario.setCedula(usuarioActualizado.getCedula());
        usuario.setRol(usuarioActualizado.getRol());
        return usuarioRepository.save(usuario);
    }
}
