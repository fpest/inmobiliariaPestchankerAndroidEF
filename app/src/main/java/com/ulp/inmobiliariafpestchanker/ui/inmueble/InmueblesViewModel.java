package com.ulp.inmobiliariafpestchanker.ui.inmueble;

import static com.ulp.inmobiliariafpestchanker.request.Token.ObtenerToken;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Propietario;
import com.ulp.inmobiliariafpestchanker.request.ApiRetrofit;
import com.ulp.inmobiliariafpestchanker.request.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Inmueble> inmuebleMutableLiveData;
    private MutableLiveData<List<Inmueble>> propiedadesMutableLiveData;
    private ApiRetrofit api;


    //
    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();

    }

    public MutableLiveData<List<Inmueble>> getPropiedadesMutableLiveData() {
        if (propiedadesMutableLiveData == null) {
            propiedadesMutableLiveData = new MutableLiveData<>();
        }
        listarPropiedades();
        return propiedadesMutableLiveData;
    }

    public void listarPropiedades() {

        String token = ObtenerToken(context);

        Call<List<Inmueble>> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().listarInmuebles(token);
        tokenPromesa.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {

                    List<Inmueble> inmuebles = response.body();

                    propiedadesMutableLiveData.setValue(inmuebles);

                } else {
                    Log.d("salida", "inmueble sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });






        // List<Inmueble> inm = api.listarInmuebles();
       // propiedadesMutableLiveData.setValue(inm);
    }

    public void setInmueble(Bundle b) {
        Inmueble i = (Inmueble) b.getSerializable("inmueble");
        inmuebleMutableLiveData.setValue(i);
    }
}

