package com.orochi.autenticacionapi.model;

import com.google.gson.annotations.SerializedName;

public class ObjetivoResponse {
    private int id;
    
    @SerializedName("tipo_objetivo")
    private String tipo_bjetivo;
    
    @SerializedName("descripcion_objetivo")
    private String descripcion_objetivo;

    public int getId() {
        return id;
    }

    public String getTipoObjetivo() {
        return tipo_bjetivo;
    }

    public String getDescripcionObjetivo() {
        return descripcion_objetivo;
    }
}
