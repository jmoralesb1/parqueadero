package com.ejemplo.parqueadero.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.parqueadero.model.Usuario;
import com.ejemplo.parqueadero.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUsuario(Usuario usuario) {
        usuario.setFechaRegistro(LocalDate.now());
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario findUsuarioByCedula(int cedula) {
        return usuarioRepository.findByCedula(cedula).orElse(null);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioActualizado){
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuario.setCedula(usuarioActualizado.getCedula());
            usuario.setRol(usuarioActualizado.getRol());
            return usuarioRepository.save(usuario);
        }).orElse(null);
    }

    //temporal
    public void deleteUsuarioById(Long id) {
    usuarioRepository.deleteById(id);
    }
}
