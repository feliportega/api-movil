package com.orochi.autenticacionapi.model;

import java.util.ArrayList;

public class LoginResponse {
    String access;

    public String getToken(){
        return access;
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
                "token='" + access + '\'' +
                ", results=" + results +
                '}';
    }
}



