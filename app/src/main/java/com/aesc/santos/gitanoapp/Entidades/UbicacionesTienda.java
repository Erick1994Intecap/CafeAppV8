package com.aesc.santos.gitanoapp.Entidades;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Android on 13/02/2018.
 */

public class UbicacionesTienda implements Serializable{
    private String txtTitle, txtUbiTienda, txtTelefono;

    @Override
    public String toString() {
        return "UbicacionesTienda{" +
                "txtTitle='" + txtTitle + '\'' +
                ", txtUbiTienda='" + txtUbiTienda + '\'' +
                ", txtTelefono='" + txtTelefono + '\'' +
                '}';
    }

    public UbicacionesTienda(){

    }

    public UbicacionesTienda(String txtTitle, String txtUbiTienda, String txtTelefono) {
        this.txtTitle = txtTitle;
        this.txtUbiTienda = txtUbiTienda;
        this.txtTelefono = txtTelefono;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtUbiTienda() {
        return txtUbiTienda;
    }

    public void setTxtUbiTienda(String txtUbiTienda) {
        this.txtUbiTienda = txtUbiTienda;
    }

    public String getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(String txtTelefono) {
        this.txtTelefono = txtTelefono;
    }
}

