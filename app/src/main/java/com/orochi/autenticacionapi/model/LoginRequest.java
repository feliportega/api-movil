package com.orochi.autenticacionapi.model;

public class LoginRequest {
     String correo;
     String password;

    public LoginRequest(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public  String getCorreo(){
        return correo;
    }
    public String getNombre(){
        return password;
    }
}
