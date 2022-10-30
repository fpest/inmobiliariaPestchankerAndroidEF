package com.ulp.inmobiliariafpestchanker.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ulp.inmobiliariafpestchanker.R;
import com.ulp.inmobiliariafpestchanker.adapter.ContratoAdapter;
import com.ulp.inmobiliariafpestchanker.adapter.InquilinoAdapter;
import com.ulp.inmobiliariafpestchanker.databinding.FragmentContratosBinding;
import com.ulp.inmobiliariafpestchanker.databinding.FragmentInquilinosBinding;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Inquilino;
import com.ulp.inmobiliariafpestchanker.request.ApiClient;
import com.ulp.inmobiliariafpestchanker.ui.pago.PagosViewModel;

import java.util.List;

public class ContratosFragment extends Fragment {
    private RecyclerView recyclerViewContratos;
    private ApiClient api= ApiClient.getApi();
    private PagosViewModel mPagViewModel;
    List<Inmueble> listaInmuebles;
    List<Inquilino> listaInquilino;


    private FragmentContratosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContratosViewModel mInmViewModel =
                new ViewModelProvider(this).get(ContratosViewModel.class);

        binding = FragmentContratosBinding.inflate(inflater, container, false);

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

        recyclerViewContratos = view.findViewById(R.id.recyclerViewContratos);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerViewContratos.setLayoutManager(gridLayoutManager);

        ContratoAdapter contratoAdapter = new ContratoAdapter(getContext(),listaInmuebles,layoutInflater);
        recyclerViewContratos.setAdapter(contratoAdapter);


    }
}