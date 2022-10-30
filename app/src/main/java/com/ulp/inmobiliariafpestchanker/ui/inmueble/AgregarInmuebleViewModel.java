package com.ulp.inmobiliariafpestchanker.ui.inmueble;

import static android.app.Activity.RESULT_OK;
import static com.ulp.inmobiliariafpestchanker.request.Token.ObtenerToken;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.Navigation;

import com.ulp.inmobiliariafpestchanker.R;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.TipoInmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Ubicacion;
import com.ulp.inmobiliariafpestchanker.request.ApiRetrofit;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel {
    //private MutableLiveData<String[]> tipoInmuebleMutableLiveData;
    private MutableLiveData<Bundle> tipoInmuebleMutableLiveData;
    //private MutableLiveData<byte[]> foto;
    private MutableLiveData<Bitmap> foto;
    private MutableLiveData<Inmueble> inmuebleCreadoLiveData;
    private MutableLiveData<Ubicacion> ubicacionInmuebleLiveData;

    private Context context;
    private Ubicacion ubicacion;
    public AgregarInmuebleViewModel(@NonNull Application application){
        super(application);
        this.context = application.getApplicationContext();
    }



    public void obtenerTipoInmueble(){

        //Call <List<TipoInmueble>> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().listarTipoInmueble(ObtenerToken(context));
        Call <List<TipoInmueble>> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().listarTipoInmueble(ObtenerToken(context));
        tokenPromesa.enqueue(new Callback<List<TipoInmueble>>() {
            //tokenPromesa.enqueue(new Callback<List<TipoInmueble>>() {
            @Override
            public void onResponse(Call<List<TipoInmueble>> call, Response<List<TipoInmueble>> response) {
                if (response.isSuccessful()) {

                    List<TipoInmueble> lista = response.body();

// Agregado
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("tipoinmueble", jsontoarray(lista));
                    bundle.putSerializable("indiceTipoInmueble", jsontoarrayIndice(lista));


                    tipoInmuebleMutableLiveData.setValue(bundle);

                } else {
                    Log.d("salida", "tipo de inmueble  sin respuesta");

                }
            }

            @Override
            public void onFailure(Call<List<TipoInmueble>> call, Throwable t) {
                Log.d("salida ", "error");

            }
        });

    }
    public String[] jsontoarray(List<TipoInmueble> lista){
        String[] tipoInm = new String[lista.size()];
        int index = 0;
        for (TipoInmueble tip : lista) {
            tipoInm[index++] = tip.getDescripcion();
        }
        return tipoInm;
    }



    public int[] jsontoarrayIndice(List<TipoInmueble> lista){
        int[] indicetipoInm = new int[lista.size()];
        int index = 0;
        for (TipoInmueble tip : lista) {
            indicetipoInm[index++] = tip.getId();
        }
        return indicetipoInm;
    }


    public MutableLiveData<Ubicacion> getUbicacionActual(){
        //ubicacionInmuebleLiveData
        if(ubicacionInmuebleLiveData==null){
            ubicacionInmuebleLiveData=new MutableLiveData<>();
        }
        return ubicacionInmuebleLiveData;
    }


    public void obtenerUbicacionInmueble() {

        ubicacion = new Ubicacion();
        SharedPreferences sp= context.getSharedPreferences("ubicacion",-1);
        //.getSharedPreferences("ubicacion",0);
        Float ubix=sp.getFloat("coordenadax",-1);
        Float ubiy=sp.getFloat("coordenaday",-1);
      ubicacion.setLatitud(ubix);
        ubicacion.setLongitud(ubiy);
         ubicacionInmuebleLiveData.setValue(ubicacion);


    }










    public MutableLiveData<Bundle> getMutableLeerTipoInmueble(){
        if(tipoInmuebleMutableLiveData==null){
            tipoInmuebleMutableLiveData=new MutableLiveData<>();
        }

        return tipoInmuebleMutableLiveData;
    }

    public void insertarInmueble(Inmueble i){




        if (i.getDireccion().isEmpty() || i.getTipouso().isEmpty()){
            Toast.makeText(context, "Los Campos Direccion y Tipo de Uso deben estar llenos", Toast.LENGTH_SHORT).show();

        }else {
            Call<Inmueble> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().registrarInmueble(ObtenerToken(context), i);
            tokenPromesa.enqueue(new Callback<Inmueble>() {

                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful()) {
                        //             inmuebleCreadoLiveData.postValue(response.body());
                        Toast.makeText(context, "Se Registró el Inmueble", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "No se pudo registrar el Inmueble. El Dato Dirección no puede estar ya como registro.", Toast.LENGTH_SHORT).show();
                        Log.d("salida", "tipo de inmueble  sin respuesta");
                    }
                }

                @Override
                public void onFailure(Call<Inmueble> call, Throwable t) {
                    Log.d("salida ", "error");
                }
            });

        }




    }
    //public MutableLiveData<byte[]> getFoto(){
        public MutableLiveData<Bitmap> getFoto(){
        if(foto==null){
            foto=new MutableLiveData<>();
        }
        return foto;
    }



    public void respuestaDeCamara(int requestCode, int resultCode, @Nullable Intent data, int REQUEST_IMAGE_CAPTURE) {
        Log.d("salida", requestCode + "");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
            byte [] b=baos.toByteArray();


      //      foto.setValue(b);

            foto.setValue(imageBitmap);
        }
    }

}

