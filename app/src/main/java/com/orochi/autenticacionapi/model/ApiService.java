package com.orochi.autenticacionapi.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("users/login/")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("users/register/")
    Call<LoginResponse> register(@Body RegisterRequest request);

    // Rutas exactas encontradas en el Swagger
    @POST("users/recuperar-contrasena/")
    Call<Void> requestPasswordReset(@Body EmailRequest request);

    @POST("users/restablecer-contrasena/")
    Call<Void> confirmPasswordReset(@Body ResetPasswordRequest request);

}
