package com.avsolucions.movil_tesis.model;

public class Sesion {
    private int id;
    private String dni; // DNI del usuario
    private String email; // Correo del usuario
    private String nombre; // Nombre del usuario
    private String estado_usuario;
    private String token; // Token de sesión
    private String rol;

    // Declarar un atributo para almacenar los datos de la sesión iniciada por el usuario
    public static Sesion DATOS_SESION;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado_usuario() {
        return estado_usuario;
    }

    public void setEstado_usuario(String estado_usuario) {
        this.estado_usuario = estado_usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
