package com.avsolucions.movil_tesis.response;

import com.avsolucions.movil_tesis.model.Sesion;

public class LoginResponse {
    private Sesion data; // Los datos de la sesión del usuario
    private String message; // Mensaje del servidor (éxito o error)
    private boolean status; // Indica si el inicio de sesión fue exitoso

    public Sesion getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}
