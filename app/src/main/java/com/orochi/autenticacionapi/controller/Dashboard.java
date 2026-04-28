package com.orochi.autenticacionapi.controller;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.orochi.autenticacionapi.R;

public class Dashboard extends AppCompatActivity {

    TextView txtnombre, txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        txtnombre = findViewById(R.id.txtNombre);
        txtemail = findViewById(R.id.txtEmail);


        Bundle extras = getIntent().getExtras();
        if (extras !=null) {
            String nombre = extras.get
        }

    }
}