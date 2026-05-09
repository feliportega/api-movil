package com.orochi.autenticacionapi.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.orochi.autenticacionapi.R;

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

        btnvolversesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverSesion = new Intent(RecuperarCuenta.this, Login.class);
                startActivity(volverSesion);
            }
        });

        btnirrestablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irRestablecer = new Intent(RecuperarCuenta.this, RestablecerContrasena.class);
                startActivity(irRestablecer);
            }
        });
    }
}