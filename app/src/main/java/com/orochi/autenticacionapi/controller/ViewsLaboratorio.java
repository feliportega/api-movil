package com.orochi.autenticacionapi.controller;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.orochi.autenticacionapi.R;
import com.orochi.autenticacionapi.model.ApiService;
import com.orochi.autenticacionapi.model.ObjetivoResponse;
import com.orochi.autenticacionapi.model.RetrofitClient;
import com.orochi.autenticacionapi.model.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewsLaboratorio extends AppCompatActivity {

    private TextView tvObjetivo,tvobj;
    private ApiService apiService;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_views_laboratorio);

        // Inicializar vistas
        tvObjetivo = findViewById(R.id.tvObjetivo);
        tvobj=findViewById(R.id.tvobj);
        
        // Inicializar ApiService y TokenManager
        apiService = RetrofitClient.getClient().create(ApiService.class);
        tokenManager = new TokenManager(this);

        // Consumir el endpoint (usando ID 1 como ejemplo)
        obtenerObjetivo(1);
    }

    private void obtenerObjetivo(int id) {
        String token = tokenManager.getToken();
        if (token == null) {
            Toast.makeText(this, "Token no encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Se agrega el prefijo "Bearer " al token para el encabezado de Authorization
        String authHeader = "Bearer " + token;
        Call<ObjetivoResponse> call = apiService.getObjetivo(authHeader, id);
        call.enqueue(new Callback<ObjetivoResponse>() {
            @Override
            public void onResponse(Call<ObjetivoResponse> call, Response<ObjetivoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ObjetivoResponse objetivo = response.body();
                    tvObjetivo.setText(objetivo.getDescripcionObjetivo());
                    tvobj.setText(objetivo.getTipoObjetivo());
                    Toast.makeText(ViewsLaboratorio.this, "objetivo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewsLaboratorio.this, "Error al obtener el objetivo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ObjetivoResponse> call, Throwable t) {
                Toast.makeText(ViewsLaboratorio.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
