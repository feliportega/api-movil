package com.orochi.autenticacionapi.model;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {
    @SerializedName("correo")
    private String correo;
    
    @SerializedName("uid")
    private String uid;
    
    @SerializedName("token")
    private String token;
    
    @SerializedName("password")
    private String password;

    public ResetPasswordRequest(String correo, String uid, String token, String password) {
        this.correo = correo;
        this.uid = uid;
        this.token = token;
        this.password = password;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
