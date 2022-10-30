package com.ulp.inmobiliariafpestchanker.ui.perfil;

import static com.ulp.inmobiliariafpestchanker.request.Token.ObtenerToken;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.ulp.inmobiliariafpestchanker.R;
import com.ulp.inmobiliariafpestchanker.modelo.Clave;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Propietario;
import com.ulp.inmobiliariafpestchanker.request.ApiRetrofit;
import com.ulp.inmobiliariafpestchanker.ui.logout.LogoutFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambioClaveViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Clave> claveMutableLiveData;
    private ApiRetrofit api;
    private Propietario p;

    public CambioClaveViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();

    }
    public LiveData<Clave> getMutableCambioClave(){
        if(claveMutableLiveData==null){
            claveMutableLiveData=new MutableLiveData<>();
        }
        return claveMutableLiveData;
    }


    public void cambioClave(Clave clave) {


        String token = ObtenerToken(context);

        if (clave.getPasswordActual().isEmpty() || clave.getPasswordNueva1().isEmpty() || clave.getPasswordNueva2().isEmpty()) {
            Toast.makeText(context, "Todos los Campos deben estar llenos", Toast.LENGTH_SHORT).show();

        } else if(!clave.getPasswordNueva1().equals(clave.getPasswordNueva2())){

            Toast.makeText(context, "Clave Nueva 1 y 2 deben ser iguales.", Toast.LENGTH_SHORT).show();


        }




        else {

            Call<Clave> clavePromesa = ApiRetrofit.getServiceInmobiliaria().actualizarClave(token, clave);
            clavePromesa.enqueue(new Callback<Clave>() {
                @Override
                public void onResponse(Call<Clave> call, Response<Clave> response) {
                    if (response.isSuccessful()) {

                        Clave mens = response.body();
                        //Log.d("clave", mens);
                        Toast.makeText(context.getApplicationContext(), mens.getMensaje().toString(), Toast.LENGTH_SHORT).show();
                        claveMutableLiveData.setValue(mens);





                    } else {
                        Log.d("clave", "Fuera");

                    }
                }

                    @Override
                    public void onFailure (Call <Clave> call, Throwable t){
                        Log.d("salida ", t.getMessage());
                        Toast.makeText(context.getApplicationContext(), "No se pudo actualizar la Clave. Verifique la información ingresada.", Toast.LENGTH_SHORT).show();



                    }


            });



        }

    }





    }