package com.ulp.inmobiliariafpestchanker.adapter;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulp.inmobiliariafpestchanker.R;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Inquilino;
import com.ulp.inmobiliariafpestchanker.ui.inmueble.InmuebleDetalleViewModel;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder>{

    private Context context;
    private List<Inmueble> listaInmuebles;
    private List<Inquilino> listaInquilinos;
    private LayoutInflater layautInflater;
    private InmuebleDetalleViewModel mViewModel;

    public ContratoAdapter(Context context, List<Inmueble> listaInmuebles, LayoutInflater layautInflater) {
        this.context = context;
        this.listaInmuebles = listaInmuebles;
        this.layautInflater = layautInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layautInflater.inflate(R.layout.detallecontrato,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble= listaInmuebles.get(position);
        String urlString = inmueble.getImagen();

        Glide.with(context)
                .load(inmueble.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmueble);




     //     holder.ivInmueble.setImageResource(R.drawable.casa);




          holder.tvDireccion.setText(inmueble.getDireccion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble );
                Navigation.findNavController(v).navigate(R.id.contratoDetalleFragment,bundle);




            }
        });

    }

    @Override
    public int getItemCount() {
        return listaInmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivInmueble;
        private TextView tvDireccion;

        public ViewHolder(@NonNull View interView){
            super(interView);
            ivInmueble = interView.findViewById(R.id.ivInmueble);
            tvDireccion = interView.findViewById(R.id.tvDireccion);



        }



    }


}
