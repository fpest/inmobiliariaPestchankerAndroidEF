package com.ulp.inmobiliariafpestchanker.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.android.material.internal.ContextUtils;
import com.ulp.inmobiliariafpestchanker.MainActivity;
import com.ulp.inmobiliariafpestchanker.modelo.Propietario;
import com.ulp.inmobiliariafpestchanker.modelo.Usuario;
import com.ulp.inmobiliariafpestchanker.request.ApiClient;
import com.ulp.inmobiliariafpestchanker.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Propietario> mPropietario;
    private MutableLiveData<Boolean> mEditEnabled;
    private MutableLiveData<String> mButtonText;
    private ApiRetrofit api;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();

    }
    public LiveData<Propietario> getMutablePropietario(){
        if(mPropietario==null){
            mPropietario=new MutableLiveData<>();
        }
        return mPropietario;
    }
    public LiveData<String> getMutableButtonText(){
        if(mButtonText==null){
            mButtonText=new MutableLiveData<>();
        }
        return mButtonText;
    }
    public LiveData<Boolean> getMutableEditEnabled(){
        if(mEditEnabled==null){
            mEditEnabled=new MutableLiveData<>();
        }
        return mEditEnabled;
    }


    private String ObtenerToken(){
        SharedPreferences sp= context.getSharedPreferences("token",0);
        String token=sp.getString("token","-1");
        return token;

    };


    public void ObtenerUsuario(){
       // Propietario p= api.obtenerUsuarioActual();


        Propietario propietario;

        SharedPreferences sp= context.getSharedPreferences("token",0);
        String token=sp.getString("token","-1");

        Call<Propietario> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().obtenerPerfil(token);
        tokenPromesa.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {

                    Propietario propietario = response.body();
                    // dentro de un callback usar postvalue y no setvalue
                    mPropietario.postValue(propietario);

                } else {
                    Log.d("salida", "propietario sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("salida ", t.getMessage());

            }
        });
















        // mPropietario.setValue(p);

    }
    public void EditarGuardar(String textoBoton, Propietario p){

        if (textoBoton.equals("EDITAR")){
            mButtonText.setValue("GUARDAR");
            mEditEnabled.setValue(true);
        }else{
            mButtonText.setValue("EDITAR");
//


            Call tokenPromesa = ApiRetrofit.getServiceInmobiliaria().actualizarPerfil(ObtenerToken(),p);
            tokenPromesa.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()) {

                        Propietario propietario = response.body();

                        Toast.makeText(context.getApplicationContext(), "Propietario Actualizado Correctamente.", Toast.LENGTH_SHORT).show();
                        mPropietario.setValue(propietario);

                    } else {
                        Log.d("salida", "propietario sin respuesta");

                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Log.d("salida ", t.getMessage());

                }
            });














            mEditEnabled.setValue(false);
        }

    }



}