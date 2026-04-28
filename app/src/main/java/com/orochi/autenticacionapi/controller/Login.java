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
import com.orochi.autenticacionapi.model.LoginRequest;
import com.orochi.autenticacionapi.model.LoginResponse;
import com.orochi.autenticacionapi.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {


    EditText edtCorreo, edtPassword;
    Button btnIngresar;
    TextView tvregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        tvregister = findViewById(R.id.tvRegister);
        tvregister.setOnClickListener(view -> {
            Intent irReg = new Intent(Login.this, Register.class);
            startActivity(irReg);
        });

        edtCorreo = findViewById(R.id.edtCorreo);
        edtPassword = findViewById(R.id.edtPassword);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(view -> {
            String email = edtCorreo.getText().toString();
            String password = edtPassword.getText().toString();

            if(email.isEmpty() || password.isEmpty()){
                edtCorreo.setError("Campo requerido");
                edtPassword.setError("Campo requerido");
                return;
            }

            LoginRequest request = new LoginRequest(email,password);

            ApiService api = RetrofitClient.getClient().create(ApiService.class);

            Call<LoginResponse> call = api.login(request);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (response.isSuccessful() && response.body() != null) {
                        String access = response.body().getToken();
                        guardarToken(access);

                        Toast.makeText(Login.this, "¡Bienbenido!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Login.this, "Correo o contraseña incorecto", Toast.LENGTH_SHORT).show();
                    }

                }

                private void guardarToken(String access){
                    System.out.println("token: "+access);
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    System.out.println("Error; "+t.getMessage());
                }
            });
        });


    }
}

