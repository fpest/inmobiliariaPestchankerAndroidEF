package com.ulp.inmobiliariafpestchanker.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ulp.inmobiliariafpestchanker.R;
import com.ulp.inmobiliariafpestchanker.adapter.InquilinoAdapter;
import com.ulp.inmobiliariafpestchanker.databinding.FragmentInquilinosBinding;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Inquilino;
import com.ulp.inmobiliariafpestchanker.request.ApiClient;

import java.util.List;

public class InquilinosFragment extends Fragment {
    private RecyclerView recyclerViewInquilino;
    private ApiClient api= ApiClient.getApi();
    private InquilinosViewModel mInmViewModel;
    List<Inmueble> listaInmuebles;
    List<Inquilino> listaInquilino;


    private FragmentInquilinosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InquilinosViewModel mInmViewModel =
                new ViewModelProvider(this).get(InquilinosViewModel.class);

        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mInmViewModel.getPropiedadesMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {

                    @Override
                    public void onChanged(List<Inmueble> inmuebles) {
                        inicializarVista(root, inflater, inmuebles);

                    }
                });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void inicializarVista(View view, LayoutInflater layoutInflater, List<Inmueble> listaInmuebles){

        recyclerViewInquilino = view.findViewById(R.id.recyclerViewInquilinos);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerViewInquilino.setLayoutManager(gridLayoutManager);

        InquilinoAdapter inquilinoAdapter = new InquilinoAdapter(getContext(),listaInmuebles,layoutInflater);
        recyclerViewInquilino.setAdapter(inquilinoAdapter);


    }
}