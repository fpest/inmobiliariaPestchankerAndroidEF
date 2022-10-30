package com.ulp.inmobiliariafpestchanker.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.inmobiliariafpestchanker.MainActivity;
import com.ulp.inmobiliariafpestchanker.modelo.Propietario;
import com.ulp.inmobiliariafpestchanker.modelo.Usuario;
import com.ulp.inmobiliariafpestchanker.request.ApiClient;
import com.ulp.inmobiliariafpestchanker.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<String> error;
    private Context context;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();

    }

    public LiveData<String> getErrorVisibility() {
        if (error == null) { error = new MutableLiveData<>(); }
        return error;
    }

    public void noRecueldoPass(String email){

        {

            Call<String> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().emailPedido(email);
            tokenPromesa.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {

                        Toast.makeText(context.getApplicationContext(), "Verifique si llegó un mail a su correo.", Toast.LENGTH_SHORT).show();


                    } else {
                        Log.d("salida", "sin respuesta");

                        if (email.isEmpty())
                            Toast.makeText(context.getApplicationContext(), "Los datos de Usuario y Contraseña deben estar cargados.", Toast.LENGTH_SHORT).show();


                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("salida ", t.getMessage());
                    error.setValue("Problemas de conexión.");
                }
            });

        }






    }


    public void login(String email, String pass) {

        Usuario usuario = new Usuario(email, pass);
        Call<String> tokenPromesa = ApiRetrofit.getServiceInmobiliaria().login(usuario);
        tokenPromesa.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("salida ", response.body());

                    String token = response.body();
                    SharedPreferences sp = context.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer " + token);
                    editor.commit();

                    Intent i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);


                } else {
                    Log.d("salida", "sin respuesta");

                    if (email.isEmpty() || pass.isEmpty())
                        error.setValue("Los datos de Usuario y Contraseña deben estar cargados.");
                    else {
                        error.setValue("Usuario o Contraseña incorrecto.");
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("salida ", t.getMessage());
                error.setValue("Problemas de conexión.");
            }
        });

    }



}
