package com.ulp.inmobiliariafpestchanker.ui.inmueble;

import static com.ulp.inmobiliariafpestchanker.request.Token.ObtenerToken;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import com.google.android.gms.maps.model.LatLng;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Propietario;
import com.ulp.inmobiliariafpestchanker.request.ApiClient;
import com.ulp.inmobiliariafpestchanker.request.ApiRetrofit;
import com.ulp.inmobiliariafpestchanker.ui.inicio.LeerMapa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inmueble> inmuebleMutableLiveData;
    private MutableLiveData<Inmueble> MutableDetalleInmueble;
    private ApiRetrofit api;
    private Inmueble i;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();

    }
    public LiveData<Inmueble> getMutableLeerInmueble(){
        if(inmuebleMutableLiveData==null){
            inmuebleMutableLiveData=new MutableLiveData<>();
        }
        return inmuebleMutableLiveData;
    }

    public void setInmueble(Bundle b) {
        i = (Inmueble)b.getSerializable("inmueble");
     inmuebleMutableLiveData.setValue(i);
    }

    public LiveData<Inmueble> getMutableDetalleInmueble(){
        if(inmuebleMutableLiveData==null){
            inmuebleMutableLiveData = new MutableLiveData<>();
        }
        return inmuebleMutableLiveData;
    }


    public void combo(Boolean checked, String codigo) {

        String token = ObtenerToken(context);
        i.setDisponible(checked);

        Call tokenPromesa = ApiRetrofit.getServiceInmobiliaria().actualizarInmueble(token, i);
        tokenPromesa.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {

                    Inmueble inmueble = response.body();

                    Toast.makeText(context.getApplicationContext(), "Inmueble Actualizado Correctamente.", Toast.LENGTH_SHORT).show();
                    inmuebleMutableLiveData.setValue(inmueble);

                } else {
                    Log.d("salida", "propietario sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });











        /*
        ArrayList<Inmueble> pro= api.obtnerPropiedades();

        for(Inmueble inmueble:pro){
            int codigoBase = inmueble.getIdInmueble();
            int codigoNumero = Integer.parseInt(codigo);
            if(inmueble.getIdInmueble()==codigoNumero){
                inmueble.setEstado(checked);
                api.actualizarInmueble(inmueble);
            }
        }
*/



        }

}