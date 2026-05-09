package com.orochi.autenticacionapi.model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("correo")
    private String correo;
    private String password;

    public LoginRequest(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }
}
