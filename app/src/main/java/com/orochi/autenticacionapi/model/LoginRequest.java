package com.orochi.autenticacionapi.model;

public class LoginRequest {
     String correo;
     String nombre;

    public LoginRequest(String correo, String nombre) {
        this.correo = correo;
        this.nombre = nombre;
    }

    public  String getCorreo(){
        return correo;
    }
    public String getNombre(){
        return nombre;
    }
}
