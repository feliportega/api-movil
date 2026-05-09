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
import com.orochi.autenticacionapi.model.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText edtCorreo, edtPassword;
    Button btnIngresar;
    TextView tvregister, tvrecuperarcuenta;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        tokenManager = new TokenManager(this);

        tvrecuperarcuenta = findViewById(R.id.tvRecuperarcuenta);
        tvrecuperarcuenta.setOnClickListener(view -> {
            Intent irRecuperar = new Intent(Login.this, RecuperarCuenta.class);
            startActivity(irRecuperar);
        });

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
                if(email.isEmpty()) edtCorreo.setError("Campo requerido");
                if(password.isEmpty()) edtPassword.setError("Campo requerido");
                return;
            }

            ejecutarLogin(email, password);
        });
    }

    private void ejecutarLogin(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);
        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        Call<LoginResponse> call = api.login(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String access = response.body().getToken();
                    tokenManager.saveToken(access);

                    Toast.makeText(Login.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();

                    // Navegamos al Dashboard
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    intent.putExtra("USER_EMAIL", email);
                    startActivity(intent);
                    finish(); 

                } else {
                    Toast.makeText(Login.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


//  btnenrol.setOnClickListener(new View.OnClickListener() {
//      @Override
//     public  void onClick(View view) {
//       if (FaceVault.hasEmbedding(Login.this)) {
//new androidx.appcompat.app.AlertDialog.Builder(Login.this)
// .setTitle("Rostro ya registrado")
//.setMessage("Ya existe un rostro guardado en este dispositivo. ¿Deseas reemplazarlo?")
//  .setPositiveButton("Sí, reemplazar", (dialog, which) -> {
//        openEnrollFlow();
//      })
//        .setNegativeButton("No", null)
//          .show();
//} else {
//      openEnrollFlow();
//    }

// }
// private void openEnrollFlow() {
//       Intent intent;
//        if (FaceVault.hasConsent(Login.this)) {
//              intent = new Intent(Login.this, FaceEnrollActivity.class);
//    } else {
//          intent = new Intent(Login.this, FaceConsentActivity.class);
//       }
//         startActivity(intent);
//      }
//    });

//     btnverify.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public  void onClick(View view) {
//               Intent goVerify = new Intent(Login.this, FaceVerifyActivity.class);
//               startActivity(goVerify);

//           }
//     })
