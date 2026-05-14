package com.orochi.autenticacionapi.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.orochi.autenticacionapi.R;
import com.orochi.autenticacionapi.model.ApiService;
import com.orochi.autenticacionapi.model.ResetPasswordRequest;
import com.orochi.autenticacionapi.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestablecerContrasena extends AppCompatActivity {

    EditText edtUid, edtToken, edtcontrasena, edtAgaincontrasena;
    Button btnNuevaContrasena;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restablecer_contrasena);

        // Inicialización de vistas coincidiendo con los IDs del XML
        edtUid = findViewById(R.id.edtUid);
        edtToken = findViewById(R.id.edtToken);
        edtcontrasena = findViewById(R.id.edtcontrasena);
        edtAgaincontrasena = findViewById(R.id.edtAgaincontrasena);
        btnNuevaContrasena = findViewById(R.id.btnNuevaContrasena);

        // Recuperamos el email que viene de la pantalla anterior
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userEmail = extras.getString("USER_EMAIL");
        }

        btnNuevaContrasena.setOnClickListener(v -> {
            String uid = edtUid.getText().toString().trim();
            String token = edtToken.getText().toString().trim();
            String pass = edtcontrasena.getText().toString().trim();
            String confirmPass = edtAgaincontrasena.getText().toString().trim();

            if (uid.isEmpty() || token.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (pass.length() < 8) {
                edtcontrasena.setError("Mínimo 8 caracteres");
                return;
            }

            if (!pass.equals(confirmPass)) {
                edtAgaincontrasena.setError("Las contraseñas no coinciden");
                return;
            }

            ejecutarCambioPassword(uid, token, pass);
        });
    }

    private void ejecutarCambioPassword(String uid, String token, String nuevaPassword) {
        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        // Usamos el modelo actualizado que incluye uid y token
        ResetPasswordRequest request = new ResetPasswordRequest(userEmail, uid, token, nuevaPassword);

        Call<Void> call = api.confirmPasswordReset(request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RestablecerContrasena.this, "Contraseña actualizada con éxito", Toast.LENGTH_LONG).show();
                    
                    // Volver al Login
                    Intent intent = new Intent(RestablecerContrasena.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RestablecerContrasena.this, "Datos inválidos o error al actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RestablecerContrasena.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
