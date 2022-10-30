package com.ulp.inmobiliariafpestchanker.ui.pago;

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
import com.ulp.inmobiliariafpestchanker.databinding.FragmentPagosBinding;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Inquilino;
import com.ulp.inmobiliariafpestchanker.modelo.Pago;

import java.util.List;

public class PagosFragment extends Fragment {
    private RecyclerView recyclerViewPagos;

    private PagosViewModel pViewModel;
    List<Inmueble> listaInmuebles;
    List<Inquilino> listaInquilino;


    private FragmentPagosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PagosViewModel pViewModel =
                new ViewModelProvider(this).get(PagosViewModel.class);

        binding = FragmentPagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pViewModel.getPagosMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {

            @Override
            public void onChanged(List<Pago> pagos) {
                inicializarVista(root, inflater, pagos);

            }
        });

        Bundle bb = getArguments();
        pViewModel.listarPagos(bb);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void inicializarVista(View view, LayoutInflater layoutInflater, List<Pago> pagos) {

        recyclerViewPagos = view.findViewById(R.id.recyclerViewPagos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerViewPagos.setLayoutManager(gridLayoutManager);


    }









 /*           .

    setInmueble(bb);
        return root;

*/
}