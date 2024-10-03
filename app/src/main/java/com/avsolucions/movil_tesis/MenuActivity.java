package com.avsolucions.movil_tesis;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import com.avsolucions.movil_tesis.R;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.avsolucions.movil_tesis.databinding.ActivityMenuBinding;
import com.avsolucions.movil_tesis.model.Sesion;
import com.avsolucions.movil_tesis.retrofit.RetrofitClient;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;

    /* Declarar los controles de la cabecera del menú */
    ImageView imgUsuario;
    TextView txtUsuario, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el layout usando ViewBinding
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar la toolbar y la navegación
        configurarNavegacion();

        // Mostrar los datos del usuario que ha iniciado sesión
        mostrarDatosUsuario();

        // Configurar la visibilidad de los botones del menú según el rol del usuario
        configurarVisibilidadBotones();
    }

    /**
     * Método para configurar la navegación del menú principal.
     */
    private void configurarNavegacion() {
        NavController navController = Navigation.findNavController(MenuActivity.this, R.id.nav_host_fragment_content_menu);

        // Configurar el DrawerLayout y el NavigationView
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Configurar los destinos del menú
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home) // Ajustar los IDs según los destinos
                .setOpenableLayout(drawer)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    /**
     * Método para mostrar los datos del usuario en la cabecera del menú.
     */
    private void mostrarDatosUsuario() {
        // Enlazar los controles de la cabecera del menú
        NavigationView navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);
        imgUsuario = headerView.findViewById(R.id.imgUsuario);
        txtUsuario = headerView.findViewById(R.id.txtUsuario);
        txtEmail = headerView.findViewById(R.id.txtEmail);

        // Mostrar los datos del usuario
        txtUsuario.setText(Sesion.DATOS_SESION.getNombre());
        txtEmail.setText(Sesion.DATOS_SESION.getEmail());

        // Cargar la imagen del usuario (si tienes una URL de imagen)
        Glide.with(MenuActivity.this)
                .load(RetrofitClient.URL_API_SERVICE + Sesion.DATOS_SESION.getDni())
                .into(imgUsuario);
    }

    /**
     * Método para configurar la visibilidad de los botones según el rol del usuario.
     */
    private void configurarVisibilidadBotones() {
        String rolUsuario = Sesion.DATOS_SESION.getRol();

        if (rolUsuario.equals("paciente")) {
            // Ocultar el botón de la opción 7 si el rol es paciente
            findViewById(R.id.btnOpcion7).setVisibility(View.GONE);
        } else {
            // Mostrar todas las opciones si es especialista o administrador
            findViewById(R.id.btnOpcion7).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú; esto agrega items a la barra de acción si está presente
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
