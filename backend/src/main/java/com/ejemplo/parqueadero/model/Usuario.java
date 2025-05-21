package com.ejemplo.parqueadero.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private int cedula;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private LocalDate fechaRegistro;

    public Usuario() {}

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getContrasena() { return contrasena; }
    public int getCedula() { return cedula; }
    public Rol getRol() { return rol; }

    @JsonProperty(access = Access.READ_ONLY)
    public LocalDate getFechaRegistro() { return fechaRegistro; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public void setCedula(int cedula) { this.cedula = cedula; }
    public void setRol(Rol rol) { this.rol = rol; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
