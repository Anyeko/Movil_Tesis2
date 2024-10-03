package com.avsolucions.movil_tesis.response;

import com.google.gson.annotations.SerializedName;
import com.avsolucions.movil_tesis.model.Sesion;

public class LoginResponse {

    // Mapeo de las claves del JSON que viene del servidor
    @SerializedName("data")  // Clave 'data' que contiene los datos de sesión del usuario
    private Sesion data;

    @SerializedName("message")  // Clave 'message' que contiene el mensaje del servidor
    private String message;

    @SerializedName("status")  // Clave 'status' que indica si el login fue exitoso
    private boolean status;

    // Getters
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
