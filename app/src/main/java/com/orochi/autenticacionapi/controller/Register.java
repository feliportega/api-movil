package com.orochi.autenticacionapi.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.orochi.autenticacionapi.R;
import com.orochi.autenticacionapi.model.ApiService;
import com.orochi.autenticacionapi.model.LoginResponse;
import com.orochi.autenticacionapi.model.RegisterRequest;
import com.orochi.autenticacionapi.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText edtNombre, edtCorreo, edtPassword;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        edtNombre = findViewById(R.id.edtNombre);
        edtCorreo= findViewById(R.id.edtCorreo);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);


        btnRegistrar.setOnClickListener(view -> {
            String nombre = edtNombre.getText().toString();
            String correo = edtCorreo.getText().toString();
            String pass = edtPassword.getText().toString();

            if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            ejecutarRegistro(nombre,correo,pass);
        });

    }

    private void ejecutarRegistro(String nombre, String correo, String pass){
        RegisterRequest request = new RegisterRequest(nombre, correo, pass);
        ApiService api = RetrofitClient.getClient().create(ApiService.class);

        Call<LoginResponse> call = api.register(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Register.this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                finish();
                } else {
                    Toast.makeText(Register.this, "Error: El usuario ya existe o datos invalidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Register.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });

    }

}