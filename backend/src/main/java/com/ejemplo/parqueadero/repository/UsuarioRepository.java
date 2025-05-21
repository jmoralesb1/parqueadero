package com.ejemplo.parqueadero.repository;

import java.util.List;
import java.util.Optional;

import com.ejemplo.parqueadero.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCedula(int cedula);
    Optional<Usuario> findByCedula(Long cedula);
    
}