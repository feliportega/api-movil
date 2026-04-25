package com.orochi.autenticacionapi.model;

import java.util.ArrayList;

public class LoginResponse {
    String token;

    public String getToken(){
        return token;
    }

    private ArrayList<LoginRequest>results;

    public LoginResponse(){
    }

    public LoginResponse(ArrayList<LoginRequest> results){
        this.results = results;
    }

    public ArrayList<LoginRequest>getResults(){
        return results;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", results=" + results +
                '}';
    }
}


