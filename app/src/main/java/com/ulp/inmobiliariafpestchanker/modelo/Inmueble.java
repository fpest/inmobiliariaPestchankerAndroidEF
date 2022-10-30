package com.ulp.inmobiliariafpestchanker.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private int cantidadAmbientes;
    private double coordenadaE;
    private double coordenadaN;
    private String direccion;
    private boolean disponible=true;
    private String tipoUso;
    private int tipoInmuebleId;
    private int propietarioId;
    private String imagen;
    private double precioInmueble;
    private TipoInmueble tipoInmueble;

    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.



    public Inmueble(int id,
                    String direccion,
                    String tipoUso,
                    int tipoInmuebleId,
                    int cantidadAmbientes,
                    double precioInmueble,
                    int propietarioId,
                    boolean disponible,
                    String imagen,
                    double coordenadaE,
                    double coordenadaN,
                    TipoInmueble tipoInmueble) {
        this.id = id;
        this.direccion = direccion;
        this.tipoUso = tipoUso;
        this.tipoInmuebleId = tipoInmuebleId;
        this.cantidadAmbientes = cantidadAmbientes;
        this.precioInmueble = precioInmueble;
        this.propietarioId = propietarioId;
        this.disponible = disponible;
        this.imagen = imagen;
        this.coordenadaE = coordenadaE;
        this.coordenadaN = coordenadaN;
        this.tipoInmueble = tipoInmueble;
    }
    public Inmueble() {

    }

    public TipoInmueble  getipoinmueble() {
        return tipoInmueble;
    }

    public void setTipoinmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public double getCoordenadaE() {
        return coordenadaE;
    }

    public void setCoordenadaE(double coordenadaE) {
        this.coordenadaE = coordenadaE;
    }

    public double getCoordenadaN() {
        return coordenadaN;
    }

    public void setCoordenadaN(double coordenadaN) {
        this.coordenadaN = coordenadaN;
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
        return tipoUso;
    }

    public void setTipouso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    public int getTipoinmuebleId() {
        return tipoInmuebleId;
    }

    public void setTipoinmuebleId(int tipoInmuebleId) {
        this.tipoInmuebleId = tipoInmuebleId;
    }

    public int getCantidadambientes() {
        return cantidadAmbientes;
    }

    public void setCantidadambientes(int cantidadAmbientes) {
        this.cantidadAmbientes = cantidadAmbientes;
    }

    public double getPrecioinmueble() {
        return precioInmueble;
    }

    public void setPrecioinmueble(double PrecioInmueble) {
        this.precioInmueble = PrecioInmueble;
    }

    public int getPropietario() {
        return propietarioId;
    }

    public void setPropietario(int propietarioId) {
        this.propietarioId = propietarioId;
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

    public void setImagen(String Imagen) {
        this.imagen = Imagen;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
