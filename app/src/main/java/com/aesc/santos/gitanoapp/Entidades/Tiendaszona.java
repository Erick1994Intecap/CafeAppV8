package com.aesc.santos.gitanoapp.Entidades;

import java.io.Serializable;

/**
 * Created by Android on 12/02/2018.
 */

public class Tiendaszona implements Serializable {
    String zona;

    public Tiendaszona() {

    }

    public Tiendaszona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Tiendaszona{" +
                "zona='" + zona + '\'' +
                '}';
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
