package com.ulp.inmobiliariafpestchanker.ui.perfil;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ulp.inmobiliariafpestchanker.R;
import com.ulp.inmobiliariafpestchanker.databinding.FragmentPerfilBinding;
import com.ulp.inmobiliariafpestchanker.modelo.Propietario;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel vmPerfil;
    private EditText etCodigo, etNombre, etApellido, etDni, etEmail, etTelefono, etPassword;
    private Button btEnviarGuardar;
    private Button btnCambiarClave;
    private ImageView ivAvatar;
    private int avatar;
    private String claveOriginal;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        vmPerfil = new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btEnviarGuardar = view.findViewById(R.id.btEnviarGuardar);

        vmPerfil.getMutablePropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etCodigo.setText(propietario.getId() + "");
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
               etDni.setText(propietario.getDni() + "");
                etEmail.setText(propietario.getEmail());
              claveOriginal = propietario.getClave();
                //etPassword.setText(propietario.getClave());
                etTelefono.setText(propietario.getTelefono() + "");
           //     avatar = propietario.getAvatar();
                ivAvatar.setImageResource(avatar);

            }
        });

        vmPerfil.getMutableEditEnabled().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean enabled) {
                etApellido.setEnabled(enabled);
                etDni.setEnabled(enabled);
                etEmail.setEnabled(enabled);
                etNombre.setEnabled(enabled);
                //etPassword.setEnabled(enabled);
                etTelefono.setEnabled(enabled);

            }
        });

        vmPerfil.getMutableButtonText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String textBoton) {
                btEnviarGuardar.setText(textBoton);
            }
        });


        inicializarVista(view);


        btEnviarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vmPerfil.EditarGuardar(btEnviarGuardar.getText().toString(), getPropietario());
            }
        });

        btnCambiarClave = view.findViewById(R.id.btnCambioClave);
        btnCambiarClave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.cambioClaveFragment);
            }
        });


        return view;
    }

    public void inicializarVista(View view) {

        etCodigo = view.findViewById(R.id.etCodigo);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etDni = view.findViewById(R.id.etDni);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etTelefono = view.findViewById(R.id.etTelfono);
        ivAvatar = view.findViewById(R.id.ivAvatar);
        btEnviarGuardar = view.findViewById(R.id.btEnviarGuardar);
        vmPerfil.ObtenerUsuario();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private Propietario getPropietario() {
        int id = Integer.parseInt(etCodigo.getText().toString());
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String dni = etDni.getText().toString();
        String email = etEmail.getText().toString();
        String clave = claveOriginal;
        String telefono = etTelefono.getText().toString();
        Propietario p = new Propietario(id, dni, nombre, apellido, email, clave, telefono, 0);

        return p;
    }
}
