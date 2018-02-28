package com.aesc.santos.gitanoapp.Utilidades;

/**
 * Created by Android on 2/19/2018.
 */

public class Utilidades {
    public static final int LIST=1;
    public static final int GRID=2;

    public static int visualizacion=LIST;


    public static final String SHARED_NAME = "gitane";
    public static final String SHARED_LIST_NAMES = "gitaneList";
    public static final boolean FRAGMENT_PRODUCTO_DETALLE = true;
    public static final boolean FRAGMENT_FAVORITOS = false;

    /***
     *
     * @param cadena cadena csv a separar.
     * @return retorna un array de valores individuales.
     */
    public String[] separar(String cadena)
    {
        try {
            String[]  retornar = cadena.split(",");
            return retornar;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("No se logro el split");
            return null;
        }

    }
}
