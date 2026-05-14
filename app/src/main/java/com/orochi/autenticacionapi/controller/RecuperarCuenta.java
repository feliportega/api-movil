package com.orochi.autenticacionapi.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.orochi.autenticacionapi.R;
import com.orochi.autenticacionapi.model.ApiService;
import com.orochi.autenticacionapi.model.EmailRequest;
import com.orochi.autenticacionapi.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecuperarCuenta extends AppCompatActivity {
    TextView btnvolversesion;
    Button btnirrestablecer;
    EditText edtemailrecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recuperar_cuenta);

        edtemailrecuperar = findViewById(R.id.etEmailRecuperar);
        btnvolversesion = findViewById(R.id.btnRegresarsesion);
        btnirrestablecer = findViewById(R.id.btnSRestableser);

        btnvolversesion.setOnClickListener(view -> {
            finish(); 
        });

        btnirrestablecer.setOnClickListener(view -> {
            String email = edtemailrecuperar.getText().toString().trim();

            if (email.isEmpty()) {
                edtemailrecuperar.setError("Ingresa tu correo");
                return;
            }

            ejecutarRecuperacion(email);
        });
    }

    private void ejecutarRecuperacion(String email) {
        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        EmailRequest request = new EmailRequest(email);

        Call<Void> call = api.requestPasswordReset(request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RecuperarCuenta.this, "Correo verificado correctamente", Toast.LENGTH_SHORT).show();
                    
                    Intent intent = new Intent(RecuperarCuenta.this, RestablecerContrasena.class);
                    intent.putExtra("USER_EMAIL", email);
                    startActivity(intent);
                } else {
                    // Mostramos el código de error para diagnosticar (404, 400, etc)
                    Toast.makeText(RecuperarCuenta.this, "Error " + response.code() + ": El correo no está registrado o ruta inválida", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RecuperarCuenta.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
