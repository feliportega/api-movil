package com.orochi.autenticacionapi.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("users/login/")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("users/register/")
    Call<LoginResponse> register(@Body RegisterRequest request);

    @POST("users/recuperar-contrasena/")
    Call<Void> requestPasswordReset(@Body EmailRequest request);

    @POST("users/restablecer-contrasena/")
    Call<Void> confirmPasswordReset(@Body ResetPasswordRequest request);

    @GET("objetivos/{id}/")
    Call<ObjetivoResponse> getObjetivo(
            @Header("Authorization") String token,
            @Path("id") int id
    );

    @GET("laboratorio-profesor/{id}/")
    Call<LabResEstudiante> getLaboratorio(
            @Header("Authorization") String token,
            @Path("id") int id
    );

}
