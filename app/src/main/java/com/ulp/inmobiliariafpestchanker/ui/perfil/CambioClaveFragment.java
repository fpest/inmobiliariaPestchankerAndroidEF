package com.ulp.inmobiliariafpestchanker.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ulp.inmobiliariafpestchanker.R;
import com.ulp.inmobiliariafpestchanker.databinding.FragmentCambioClaveBinding;
import com.ulp.inmobiliariafpestchanker.databinding.FragmentInmuebleDetalleBinding;
import com.ulp.inmobiliariafpestchanker.modelo.Clave;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.ui.inmueble.InmuebleDetalleViewModel;

import java.util.List;

public class CambioClaveFragment extends Fragment {

    private CambioClaveViewModel mViewModel;

    private Button btnCambiarClaveABase;
    private FragmentCambioClaveBinding binding;
    //public static CambioClaveFragment newInstance() {
     //   return new CambioClaveFragment();
   // }

    //@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       mViewModel = new ViewModelProvider(this).get(CambioClaveViewModel.class);
        binding = FragmentCambioClaveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inicializarVista(root, inflater);


        mViewModel.getMutableCambioClave().observe(getViewLifecycleOwner(), new Observer<Clave>() {


            @Override
            public void onChanged(Clave clave) {
                Navigation.findNavController(root).navigate(R.id.nav_perfil);
            }

        });








        return root;
        //return inflater.inflate(R.layout.fragment_cambio_clave, container, false);






    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CambioClaveViewModel.class);
        // TODO: Use the ViewModel
    }

    public void inicializarVista(View view, LayoutInflater layoutInflater)
    {
        btnCambiarClaveABase = view.findViewById((R.id.btnCambiarClaveABase));
        btnCambiarClaveABase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String claveActual = binding.etPasswordActual.getText().toString();
                String claveNueva1 = binding.etPasswordNueva1.getText().toString();
                String claveNueva2 = binding.etPasswordNueva2.getText().toString();
                Clave clave = new Clave(claveActual, claveNueva1, claveNueva2, "");



                mViewModel.cambioClave(clave);

                binding.etPasswordActual.setText("");
                binding.etPasswordNueva1.setText("");
                binding.etPasswordNueva2.setText("");

            }
        });




    }

}