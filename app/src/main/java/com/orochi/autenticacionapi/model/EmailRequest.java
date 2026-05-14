package com.orochi.autenticacionapi.model;

import com.google.gson.annotations.SerializedName;

public class EmailRequest {
    @SerializedName("correo")
    private String correo;

    public EmailRequest(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
