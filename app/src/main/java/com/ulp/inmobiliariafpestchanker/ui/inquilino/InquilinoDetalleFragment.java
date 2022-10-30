package com.ulp.inmobiliariafpestchanker.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulp.inmobiliariafpestchanker.databinding.FragmentInmuebleDetalleBinding;
import com.ulp.inmobiliariafpestchanker.databinding.FragmentInquilinoDetalleBinding;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Inquilino;


public class InquilinoDetalleFragment extends Fragment {


    private InquilinoDetalleViewModel mViewModel;
    private FragmentInquilinoDetalleBinding binding;


    public static InquilinoDetalleFragment newInstance() {
        return new InquilinoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        binding = FragmentInquilinoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mViewModel.getMutableDetalleInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino i) {
                // TODO: cargar datos en las vistas
                binding.tvCodigo.setText(i.getIdInquilino() + "");
                binding.tvNombre.setText(String.valueOf(i.getNombre() + ""));
                binding.tvApellido.setText(i.getApellido() + "");
                binding.tvDni.setText(i.getDNI() + "");
                binding.tvTelefono.setText(String.valueOf(i.getTelefono() + ""));
                binding.tvEmail.setText(i.getEmail() + " ");
                binding.tvGarante.setText(i.getNombreGarante() + " ");
                binding.tvTelefonoGarante.setText(i.getTelefonoGarante() + " ");


            }

        });


        Bundle bb = getArguments();
        mViewModel.setInmueble(bb);
        return root;





    }
}