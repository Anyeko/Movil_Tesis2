package com.avsolucions.movil_tesis.model;

import com.google.gson.annotations.SerializedName;

public class Sesion {

    // Variable estática para almacenar los datos de sesión
    public static Sesion DATOS_SESION;

    @SerializedName("ID_usuario")  // Mapeo para ID del usuario
    private int idUsuario;

    @SerializedName("correo")  // Mapeo para el correo del usuario
    private String correo;

    @SerializedName("tipo_usuario")  // Mapeo para el tipo de usuario (paciente, administrador, etc.)
    private String tipoUsuario;

    @SerializedName("estado")  // Mapeo para el estado (activo o inactivo)
    private String estado;

    // Getters and Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
