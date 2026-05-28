package com.orochi.autenticacionapi.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.orochi.autenticacionapi.R;
import com.orochi.autenticacionapi.model.TokenManager;

public class Dashboard extends AppCompatActivity {

    TextView txtnombre, txtemail;
    Button btnLogout, btnirlab;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        txtnombre = findViewById(R.id.txtNombre);
        txtemail = findViewById(R.id.txtEmail);
        btnLogout = findViewById(R.id.btnLogout);

        btnirlab = findViewById(R.id.btnIrlabo);
        btnirlab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irLab = new Intent(Dashboard.this, ViewsLaboratorio.class);
                startActivity(irLab);
            }
        });
        
        tokenManager = new TokenManager(this);

        // Recibimos los datos del Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String email = extras.getString("USER_EMAIL");
            String nombre = extras.getString("USER_NAME");
            
            txtemail.setText(email);
            if (nombre != null && !nombre.isEmpty()) {
                txtnombre.setText(nombre);
            } else {
                txtnombre.setText("Usuario Activo");
            }
        }

        // Lógica para cerrar sesión
        btnLogout.setOnClickListener(v -> {
            tokenManager.clearToken();
            Intent intent = new Intent(Dashboard.this, Login.class);
            startActivity(intent);
            finish();
        });
    }
}
