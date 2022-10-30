package com.ulp.inmobiliariafpestchanker.modelo;

import java.io.Serializable;

public class Clave implements Serializable {

    private String passwordActual;
    private String passwordNueva1;
    private String passwordNueva2;
    private String mensaje;


    public Clave() {
    }

    public Clave(String passwordActual, String passwordNueva1, String passwordNueva2, String  mensaje) {
        this.passwordActual = passwordActual;
        this.passwordNueva1 = passwordNueva1;
        this.passwordNueva2 = passwordNueva2;
        this.mensaje = mensaje;

    }

    public String getPasswordActual() {
        return passwordActual;
    }

    public void setPasswordActual(String passwordActual) {
        passwordActual = passwordActual;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        mensaje = mensaje;
    }



    public String getPasswordNueva1() {
        return passwordNueva1;
    }

    public void setPasswordNueva1(String passwordNueva1) {
        passwordNueva1 = passwordNueva1;
    }

    public String getPasswordNueva2() {
        return passwordNueva2;
    }

    public void setPasswordNueva2(String passwordNueva2) {
        passwordNueva2 = passwordNueva2;
    }
}