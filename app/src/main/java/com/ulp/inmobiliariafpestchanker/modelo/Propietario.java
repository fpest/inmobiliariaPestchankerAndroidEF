package com.ulp.inmobiliariafpestchanker.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Propietario implements Serializable {

    private String nombre;
    private String email;
    private String clave;
    private String telefono;
    private int id;
    private String apellido;
    private String dni;
    private int avatar;


    public Propietario(){}

    public Propietario(int id, String dni, String nombre, String apellido, String email, String clave, String telefono, int avatar) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.telefono = telefono;
        this.dni = dni;
        this.avatar=avatar;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String nombre) {
        this.dni = dni;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {return apellido;}

    public void setApellido(String  apellido) {
        this.apellido = apellido;
    }

    public int getAvatar() {
        return avatar;
    }
    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Propietario that = (Propietario) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
