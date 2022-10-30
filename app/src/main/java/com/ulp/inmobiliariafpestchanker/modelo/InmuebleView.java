package com.ulp.inmobiliariafpestchanker.modelo;

import java.io.Serializable;
import java.util.Objects;

public class InmuebleView implements Serializable {

    private int id;
    private String direccion;
    private String tipouso;
    private String tipoinmueble;
    private int CantidadAmbientes;
    private double precioinmueble;
    private Propietario duenio;
    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.
    private boolean disponible=true;
    private String imagen;

    public InmuebleView(int id, String direccion, String tipouso, String tipoinmueble, int CantidadAmbientes, double precioinmueble, Propietario propietario, boolean disponible, String imagen) {
        this.id = id;
        this.direccion = direccion;
        this.tipouso = tipouso;
        this.tipoinmueble = tipoinmueble;
        this.CantidadAmbientes = CantidadAmbientes;
        this.precioinmueble = precioinmueble;
        this.duenio = propietario;
        this.disponible = disponible;
        this.imagen = imagen;
    }
    public InmuebleView() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipouso() {
        return tipouso;
    }

    public void setTipouso(String tipouso) {
        this.tipouso = tipouso;
    }

    public String getTipoinmueble() {
        return tipoinmueble;
    }

    public void setTipoinmueble(String tipoinmueble) {
        this.tipoinmueble = tipoinmueble;
    }

    public int getCantidadambientes() {
        return CantidadAmbientes;
    }

    public void setCantidadambientes(int CantidadAmbientes) {
        this.CantidadAmbientes = CantidadAmbientes;
    }

    public double getPrecioinmueble() {
        return precioinmueble;
    }

    public void setPrecioinmueble(double precioinmueble) {
        this.precioinmueble = precioinmueble;
    }

    public Propietario getPropietario() {
        return duenio;
    }

    public void setPropietario(Propietario duenio) {
        this.duenio = duenio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InmuebleView inmueble = (InmuebleView) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
