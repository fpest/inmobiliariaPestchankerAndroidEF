package com.ulp.inmobiliariafpestchanker.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ulp.inmobiliariafpestchanker.R;
import com.ulp.inmobiliariafpestchanker.adapter.InmuebleAdapter;
import com.ulp.inmobiliariafpestchanker.databinding.FragmentInmueblesBinding;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;

import java.util.List;

public class InmueblesFragment extends Fragment {
    private RecyclerView recyclerViewInmueble;

    private InmueblesViewModel mInmViewModel;
    List<Inmueble> listaInmuebles;
    private Button btnAgregar;

    private FragmentInmueblesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        InmueblesViewModel mInmViewModel =
                new ViewModelProvider(this).get(InmueblesViewModel.class);

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //mInmViewModel es el punto
        mInmViewModel.getPropiedadesMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {


            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                inicializarVista(root, inflater,inmuebles);
            }

        });
    return root;
    }




    public void inicializarVista(View view, LayoutInflater layoutInflater, List<Inmueble> listaInmuebles){

        recyclerViewInmueble = view.findViewById(R.id.recyclerViewInmuebles);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerViewInmueble.setLayoutManager(gridLayoutManager);
        InmuebleAdapter inmuebleAdapter = new InmuebleAdapter(getContext(),listaInmuebles,layoutInflater);
        recyclerViewInmueble.setAdapter(inmuebleAdapter);

        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.agregar_Inmueble);
            }
        });



    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}