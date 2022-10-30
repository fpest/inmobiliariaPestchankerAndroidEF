package com.ulp.inmobiliariafpestchanker.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Ubicacion implements Serializable {

    private Float latitud;
    private Float longitud;


    public Ubicacion() {
    }

    public Ubicacion(Float latitud, Float longitud) {
        this.latitud = latitud;
        this.longitud = longitud;

    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

}