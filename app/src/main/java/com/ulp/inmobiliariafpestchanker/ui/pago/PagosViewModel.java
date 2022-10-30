package com.ulp.inmobiliariafpestchanker.ui.pago;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.inmobiliariafpestchanker.modelo.Contrato;
import com.ulp.inmobiliariafpestchanker.modelo.Inmueble;
import com.ulp.inmobiliariafpestchanker.modelo.Inquilino;
import com.ulp.inmobiliariafpestchanker.modelo.Pago;
import com.ulp.inmobiliariafpestchanker.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class PagosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Pago>> pagosMutableLiveData;
    private MutableLiveData<List<Inmueble>> propiedadesMutableLiveData;
    private ApiClient api;

    //
    public PagosViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.api = ApiClient.getApi();
    }


    public MutableLiveData<List<Pago>> getPagosMutableLiveData() {
        if (pagosMutableLiveData == null) {
            pagosMutableLiveData = new MutableLiveData<>();
        }
        //
        return pagosMutableLiveData;
    }

    public void listarPagos(Bundle b) {
        Contrato c = (Contrato) b.getSerializable("contrato");



        List<Pago> pag = api.obtenerPagos(c);
        pagosMutableLiveData.setValue(pag);
    }

}

