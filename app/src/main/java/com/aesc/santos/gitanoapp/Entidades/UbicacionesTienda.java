package com.aesc.santos.gitanoapp.Entidades;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Android on 13/02/2018.
 */

public class UbicacionesTienda implements Serializable{
    private String txtDescripcionTienda;
    private int fotoTienda;

    public UbicacionesTienda(String txtDescripcionTienda, int fotoTienda) {
        this.txtDescripcionTienda = txtDescripcionTienda;
        this.fotoTienda = fotoTienda;
    }

    public UbicacionesTienda() {

    }

    @Override
    public String toString() {
        return "UbicacionesTienda{" +
                "txtDescripcionTienda='" + txtDescripcionTienda + '\'' +
                ", fotoTienda=" + fotoTienda +
                '}';
    }

    public String getTxtDescripcionTienda() {
        return txtDescripcionTienda;
    }

    public void setTxtDescripcionTienda(String txtDescripcionTienda) {
        this.txtDescripcionTienda = txtDescripcionTienda;
    }

    public int getFotoTienda() {
        return fotoTienda;
    }

    public void setFotoTienda(int fotoTienda) {
        this.fotoTienda = fotoTienda;
    }
}
