package com.ulp.inmobiliariafpestchanker.ui.contrato   ;

import static com.ulp.inmobiliariafpestchanker.request.Token.ObtenerToken;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import com.google.android.gms.maps.model.LatLng;
import com.ulp.inmobiliariafpestchanker.modelo.Contrato;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Inquilino;
import com.ulp.inmobiliariafpestchanker.request.ApiClient;
import com.ulp.inmobiliariafpestchanker.request.ApiRetrofit;
import com.ulp.inmobiliariafpestchanker.ui.inicio.LeerMapa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoDetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Contrato> contratoMutableLiveData;
    private ApiClient api;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
        this.api = ApiClient.getApi();
    }
    public LiveData<Contrato> getMutableLeerContrato(){
        if(contratoMutableLiveData==null){
            contratoMutableLiveData=new MutableLiveData<>();
        }
        return contratoMutableLiveData;
    }

    public void setInmueble(Bundle b) {
        Inmueble i = (Inmueble) b.getSerializable("inmueble");

        String token = ObtenerToken(context);

        Call<Contrato> inmuPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerContrato(token, i);
        inmuPromesa.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful()) {

                    Contrato contrato = response.body();

                    contrato.setFechaFin(arreglarFecha(contrato.getFechaFin()));
                    contrato.setFechaInicio(arreglarFecha(contrato.getFechaInicio()));

                    contratoMutableLiveData.setValue(contrato);

                } else {
                    Log.d("salida", "inmueble sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });



    }

    public LiveData<Contrato> getMutableDetalleContrato(){
        if(contratoMutableLiveData==null){
            contratoMutableLiveData = new MutableLiveData<>();
        }
        return contratoMutableLiveData;
    }

    public String arreglarFecha(String fecha){
    String fechaNueva, dia, mes, year;
    year = fecha.substring(0,4);
    mes = fecha.substring(5,7);
    dia = fecha.substring(8,10);
    fechaNueva = dia + "-" + mes + "-" + year;


    return fechaNueva;
    }
}