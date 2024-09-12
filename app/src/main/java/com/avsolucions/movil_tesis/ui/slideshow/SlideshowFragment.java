package com.avsolucions.movil_tesis.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.avsolucions.movil_tesis.MainActivity;
import com.avsolucions.movil_tesis.R;
import androidx.lifecycle.ViewModelProvider;
import com.avsolucions.movil_tesis.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Referencia al botón
        ImageButton btnAdultoMayor = view.findViewById(R.id.btn_adulto_mayor);

        // Agregar un listener para redirigir al LoginActivity
        btnAdultoMayor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a LoginActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}