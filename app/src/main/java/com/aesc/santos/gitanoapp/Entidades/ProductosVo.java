package com.aesc.santos.gitanoapp.Entidades;

import java.io.Serializable;

/**
 * Created by Android on 2/5/2018.
 */

public class ProductosVo implements Serializable{
    private String nombre;
    private String descripcion;
    private int imageid;

    public ProductosVo() {
    }

    public ProductosVo(String nombre, String descripcion, int imageid) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imageid = imageid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}
