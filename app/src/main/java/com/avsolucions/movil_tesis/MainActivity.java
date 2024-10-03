package com.avsolucions.movil_tesis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.avsolucions.movil_tesis.databinding.ActivityMainBinding;
import com.avsolucions.movil_tesis.model.Sesion;
import com.avsolucions.movil_tesis.response.LoginResponse;
import com.avsolucions.movil_tesis.retrofit.ApiService;
import com.avsolucions.movil_tesis.retrofit.RetrofitClient;
import com.avsolucions.movil_tesis.util.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Listener del botón de inicio de sesión
        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        Log.e("LOGIN", "INICIANDO SESIÓN");

        // Obtener las credenciales (DNI o correo y contraseña)
        String identificador = binding.txtIdentificador.getText().toString();
        String contrasena = binding.txtContrasena.getText().toString(); // Aquí ya no se usa MD5

        Log.e("LOGIN", "Identificador: " + identificador + ", Contrasena: " + contrasena);

        if (identificador.isEmpty() || contrasena.isEmpty()) {
            Helper.mensajeError(MainActivity.this, "Error de inicio de sesión", "Por favor ingresa tu usuario y contraseña");
            return;
        }

        // Crear una instancia de ApiService para hacer la petición al servicio web
        ApiService apiService = RetrofitClient.createService();

        // Realizar la petición al servicio web
        Call<LoginResponse> call = apiService.login(identificador, contrasena);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) { // Login exitoso
                    LoginResponse loginResponse = response.body();

                    if (loginResponse != null) {
                        boolean status = loginResponse.isStatus();
                        if (status) {
                            // Extraer los datos de sesión
                            Sesion sesion = loginResponse.getData();
                            if (sesion != null) {
                                String nombre = sesion.getCorreo(); // O cualquier otro dato que necesites
                                Log.e("LOGIN", "Nombre de usuario: " + nombre);

                                // Almacenar los datos de la sesión
                                Sesion.DATOS_SESION = loginResponse.getData();

                                // Redirigir al menú principal
                                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                startActivity(intent);

                                // Cerrar la actividad actual
                                MainActivity.this.finish();
                            } else {
                                Log.e("LOGIN", "Datos de sesión nulos.");
                                Helper.mensajeError(MainActivity.this, "Error de sesión", "No se pudieron recuperar los datos de sesión.");
                            }
                        } else {
                            Helper.mensajeError(MainActivity.this, "Error de inicio de sesión", "Credenciales incorrectas o cuenta inactiva");
                        }
                    } else {
                        Log.e("LOGIN", "Respuesta nula del servidor.");
                        Helper.mensajeError(MainActivity.this, "Error de inicio de sesión", "Error al procesar la respuesta del servidor.");
                    }

                } else { // Error en la autenticación (códigos 401 o 500)
                    try {
                        JSONObject jsonObjectError = new JSONObject(response.errorBody().string());
                        String message = jsonObjectError.getString("message");
                        Helper.mensajeError(MainActivity.this, "Error de Login", message);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Error de conexión
                Helper.mensajeError(MainActivity.this, "Error de conexión", t.toString());
            }
        });
    }
}
