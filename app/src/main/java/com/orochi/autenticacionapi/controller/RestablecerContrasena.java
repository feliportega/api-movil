package com.orochi.autenticacionapi.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.orochi.autenticacionapi.R;

public class RestablecerContrasena extends AppCompatActivity {

    EditText edtcontrasena, edtAgaincontrasena;
    Button btnNuevacontraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restablecer_contrasena);

        edtcontrasena = findViewById(R.id.edtcontrasena);
        edtAgaincontrasena = findViewById(R.id.edtAgaincontrasena);
        btnNuevacontraseña = findViewById(R.id.btnNuevacontraseña);



    }
}