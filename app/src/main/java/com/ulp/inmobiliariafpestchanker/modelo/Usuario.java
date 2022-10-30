package com.ulp.inmobiliariafpestchanker.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private String Usuario;
    private String Clave;


    public Usuario(){}

    public Usuario(String Usuario, String Clave) {
        this.Usuario = Usuario;
        this.Clave = Clave;


    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }



}
