package com.ulp.inmobiliariafpestchanker.modelo;

import java.io.Serializable;
import java.util.Objects;

public class TipoInmueble implements Serializable {

    private int id;
    private String descripcion;


    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.

    public TipoInmueble() {}
    public TipoInmueble(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoInmueble tipoinmueble = (TipoInmueble) o;
        return id == tipoinmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
