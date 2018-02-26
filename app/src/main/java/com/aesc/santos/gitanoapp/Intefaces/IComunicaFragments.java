package com.aesc.santos.gitanoapp.Intefaces;

import com.aesc.santos.gitanoapp.Entidades.ProductosVo;
import com.aesc.santos.gitanoapp.Entidades.Usuario;

/**
 * Created by Android on 2/6/2018.
 */

public interface IComunicaFragments {

    public void datosUsuario(Usuario usuario);
    public void enviarProducto(int position);
    public void enviarZonas(int position);

}
