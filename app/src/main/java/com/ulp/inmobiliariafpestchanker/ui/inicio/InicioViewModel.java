package com.ulp.inmobiliariafpestchanker.ui.inicio;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;


public class InicioViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<LeerMapa> mutableLeerMapa;
    private LatLng ua;

    public InicioViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
    }
    public LiveData<LeerMapa> getMutableLeerMapa(){
        if(mutableLeerMapa==null){
            mutableLeerMapa=new MutableLiveData<>();
        }
        return mutableLeerMapa;
    }

    public void leerMapa(){

        mutableLeerMapa.setValue(new LeerMapa(context));
    }


}