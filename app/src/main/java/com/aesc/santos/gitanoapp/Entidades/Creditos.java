package com.aesc.santos.gitanoapp.Entidades;

/**
 * Created by Android on 27/02/2018.
 */

public class Creditos {
    String url_perfil;
    String nombre;
    String puestos;

    public Creditos() {

    }

    public Creditos(String url_perfil, String nombre, String puestos) {
        this.url_perfil = url_perfil;
        this.nombre = nombre;
        this.puestos=puestos;
    }

    @Override
    public String toString() {
        return "Creditos{" +
                "url_perfil='" + url_perfil + '\'' +
                ", nombre='" + nombre + '\'' +
                ", puestos='" + puestos + '\'' +
                '}';
    }

    public String getPuestos() {
        return puestos;
    }

    public void setPuestos(String puestos) {
        this.puestos = puestos;
    }

    public String getUrl_perfil() {
        return url_perfil;
    }

    public void setUrl_perfil(String url_perfil) {
        this.url_perfil = url_perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
