package com.ulp.inmobiliariafpestchanker.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Inquilino;
import com.ulp.inmobiliariafpestchanker.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class InquilinosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Inquilino>> inquilinosMutableLiveData;
    private MutableLiveData<List<Inmueble>> propiedadesMutableLiveData;
    private ApiClient api;

    //
    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.api = ApiClient.getApi();
    }

    public MutableLiveData<List<Inmueble>> getPropiedadesMutableLiveData() {
        if (propiedadesMutableLiveData == null) {
            propiedadesMutableLiveData = new MutableLiveData<>();
        }
        listarPropiedades();
        return propiedadesMutableLiveData;
    }

    public void listarPropiedades() {
        List<Inmueble> inm = api.obtenerPropiedadesAlquiladas();
        propiedadesMutableLiveData.setValue(inm);
    }

    public MutableLiveData<List<Inquilino>> getInquilinosMutableLiveData() {
        if (inquilinosMutableLiveData == null) {
            inquilinosMutableLiveData = new MutableLiveData<>();
        }
        listarInquilinos();
        return inquilinosMutableLiveData;
    }

    public void listarInquilinos() {
        List<Inmueble> inq = api.obtnerPropiedades();
        propiedadesMutableLiveData.setValue(inq);
    }

}

