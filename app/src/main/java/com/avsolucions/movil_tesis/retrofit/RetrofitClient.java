package com.avsolucions.movil_tesis.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class RetrofitClient {
    public static final String URL_API_SERVICE = "http://161.132.56.198:5000"; // URL de tu servidor

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            // Crear un interceptor para loguear el cuerpo de la solicitud y la respuesta
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging) // Agregar el interceptor a OkHttp
                    .build();

            // Construir Retrofit con el cliente OkHttp modificado
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_API_SERVICE)
                    .client(client) // Usar el cliente que tiene el interceptor de logging
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService createService() {
        return getClient().create(ApiService.class);
    }
}
