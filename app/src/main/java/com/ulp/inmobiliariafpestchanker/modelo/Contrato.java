package com.ulp.inmobiliariafpestchanker.modelo;

import java.io.Serializable;
import java.sql.Date;
import java.time.DateTimeException;
import java.util.Objects;

public class Contrato implements Serializable {

    private int id;
    //private int PropietarioId;
    private String fechaInicio;
    private String fechaFin;
    private double precio;
    private com.ulp.inmobiliariafpestchanker.modelo.Inquilino inquilino;
    private com.ulp.inmobiliariafpestchanker.modelo.Inmueble inmueble;

    public Contrato() {}



    public Contrato(int id,
                    String fechaInicio,
                    String fechaFin,
                    double precio,
                    com.ulp.inmobiliariafpestchanker.modelo.Inquilino inquilino,
                    com.ulp.inmobiliariafpestchanker.modelo.Inmueble inmueble) {

        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
