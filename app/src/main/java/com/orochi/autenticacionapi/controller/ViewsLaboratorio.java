package com.orochi.autenticacionapi.controller;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.orochi.autenticacionapi.R;
import com.orochi.autenticacionapi.model.ApiService;
import com.orochi.autenticacionapi.model.ObjetivoResponse;
import com.orochi.autenticacionapi.model.LabResEstudiante;
import com.orochi.autenticacionapi.model.RetrofitClient;
import com.orochi.autenticacionapi.model.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewsLaboratorio extends AppCompatActivity {

    private TextView tvObjetivo,tvobj,tvConfig;
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
        tvConfig=findViewById(R.id.tvConfig);

        // Inicializar ApiService y TokenManager
        apiService = RetrofitClient.getClient().create(ApiService.class);
        tokenManager = new TokenManager(this);

        // Consumir el endpoint (usando ID 1 como ejemplo)
        obtenerObjetivo(1);
        cargarLaboratorio(2);
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

    private void cargarLaboratorio(int id) {
        String token = tokenManager.getToken();
        if (token == null) {
            Toast.makeText(this, "Token no encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        String authHeader = "Bearer " + token;
        Call<LabResEstudiante> call = apiService.getLaboratorio(authHeader, id);
        call.enqueue(new Callback<LabResEstudiante>() {
            @Override
            public void onResponse(Call<LabResEstudiante> call, Response<LabResEstudiante> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LabResEstudiante lab = response.body();
                    tvConfig.setText(lab.getTituloLab());
                    // Aquí puedes actualizar otras vistas con la información del laboratorio
                } else {
                    Toast.makeText(ViewsLaboratorio.this, "Error al obtener el laboratorio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LabResEstudiante> call, Throwable t) {
                Toast.makeText(ViewsLaboratorio.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
