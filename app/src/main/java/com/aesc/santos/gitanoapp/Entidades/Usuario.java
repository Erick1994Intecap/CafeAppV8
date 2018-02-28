package com.aesc.santos.gitanoapp.Entidades;

import java.io.Serializable;

/**
 * Created by Android on 2/23/2018.
 */

public class Usuario implements Serializable {
    public String nombre;
    public String apellido;
    public int puntos;
    public long DPI;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int puntos, int DPI) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntos = puntos;
        this.DPI = DPI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public long getDPI() {
        return DPI;
    }

    public void setDPI(long DPI) {
        this.DPI = DPI;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", puntos=" + puntos +
                ", DPI='" + DPI + '\'' +
                '}';
    }
}
