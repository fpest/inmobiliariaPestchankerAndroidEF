package com.ulp.inmobiliariafpestchanker.ui.inquilino   ;

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
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Inquilino;
import com.ulp.inmobiliariafpestchanker.request.ApiClient;
import com.ulp.inmobiliariafpestchanker.ui.inicio.LeerMapa;

import java.util.ArrayList;
import java.util.List;

public class InquilinoDetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inquilino> inquilinoMutableLiveData;
    private ApiClient api;

    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
        this.api = ApiClient.getApi();
    }
    public LiveData<Inquilino> getMutableLeerInquilino(){
        if(inquilinoMutableLiveData==null){
            inquilinoMutableLiveData=new MutableLiveData<>();
        }
        return inquilinoMutableLiveData;
    }

    public void setInmueble(Bundle b) {
        Inmueble i = (Inmueble) b.getSerializable("inmueble");
        Inquilino inq = api.obtenerInquilino(i);


        inquilinoMutableLiveData.setValue(inq);
    }

    public LiveData<Inquilino> getMutableDetalleInquilino(){
        if(inquilinoMutableLiveData==null){
            inquilinoMutableLiveData = new MutableLiveData<>();
        }
        return inquilinoMutableLiveData;
    }



}