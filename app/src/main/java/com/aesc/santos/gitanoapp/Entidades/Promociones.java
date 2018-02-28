package com.aesc.santos.gitanoapp.Entidades;

/**
 * Created by Android on 2/28/2018.
 */

public class Promociones {
    public String nombreP;
    public String descP;
    public String imgP;
    public String fechaInP;
    public String fechaCaP;

    public Promociones() {
    }

    public Promociones(String nombreP, String descP, String imgP, String fechaInP, String fechaCaP) {
        this.nombreP = nombreP;
        this.descP = descP;
        this.imgP = imgP;
        this.fechaInP = fechaInP;
        this.fechaCaP = fechaCaP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescP() {
        return descP;
    }

    public void setDescP(String descP) {
        this.descP = descP;
    }

    public String getImgP() {
        return imgP;
    }

    public void setImgP(String imgP) {
        this.imgP = imgP;
    }

    public String getFechaInP() {
        return fechaInP;
    }

    public void setFechaInP(String fechaInP) {
        this.fechaInP = fechaInP;
    }

    public String getFechaCaP() {
        return fechaCaP;
    }

    public void setFechaCaP(String fechaCaP) {
        this.fechaCaP = fechaCaP;
    }

    @Override
    public String toString() {
        return "PromocionesAdapter{" +
                "nombreP='" + nombreP + '\'' +
                ", descP='" + descP + '\'' +
                ", imgP='" + imgP + '\'' +
                ", fechaInP='" + fechaInP + '\'' +
                ", fechaCaP='" + fechaCaP + '\'' +
                '}';
    }
}
