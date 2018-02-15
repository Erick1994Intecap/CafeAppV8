package com.aesc.santos.gitanoapp.Entidades;

import java.io.Serializable;

/**
 * Created by Android on 2/5/2018.
 */

public class ProductosVo implements Serializable{
    private String nombre;
    private String descripcion;
    private String precio;
    public String image_url;

    public ProductosVo() {
    }

    public ProductosVo(String nombre, String descripcion, String precio, String image_url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.image_url = image_url;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "ProductosVo{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
