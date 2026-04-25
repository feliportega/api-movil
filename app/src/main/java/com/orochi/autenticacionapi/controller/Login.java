package com.orochi.autenticacionapi.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.orochi.autenticacionapi.R;
import com.orochi.autenticacionapi.model.ApiService;
import com.orochi.autenticacionapi.model.LoginRequest;
import com.orochi.autenticacionapi.model.LoginResponse;
import com.orochi.autenticacionapi.model.RetrofitClient;

import retrofit2.Call;

public class Login extends AppCompatActivity {

    EditText edtCorreo, edtPassword;
    Button btnIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        edtCorreo = findViewById(R.id.edtCorreo);
        edtPassword = findViewById(R.id.edtPassword);
        btnIngresar = findViewById(R.id.btnIngresar);


        ApiService api = RetrofitClient.getClient().create(ApiService.class);

        LoginRequest request = new LoginRequest("andres@admin.com", "123456");

        Call<LoginResponse> call = api.login(request);

    }
}

