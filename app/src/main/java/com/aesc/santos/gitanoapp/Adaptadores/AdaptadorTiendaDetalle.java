package com.aesc.santos.gitanoapp.Adaptadores;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aesc.santos.gitanoapp.Entidades.ProductosVo;
import com.aesc.santos.gitanoapp.Entidades.UbicacionesTienda;
import com.aesc.santos.gitanoapp.R;

import java.util.ArrayList;

/**
 * Created by Android on 2/5/2018.
 */

public class AdaptadorTiendaDetalle extends RecyclerView.Adapter<AdaptadorTiendaDetalle.ProductosViewHolder> implements View.OnClickListener {

    ArrayList<UbicacionesTienda> ubicacionesTiendas;
    private View.OnClickListener listener;

    public AdaptadorTiendaDetalle(ArrayList<UbicacionesTienda> ubicacionesTiendas) {
        this.ubicacionesTiendas = ubicacionesTiendas;
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_tienda_detalle, parent, false);
        view.setOnClickListener(this);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder holder, int position) {

        // holder.txtNombre.setText(listaProductos.get(position).getNombre());
        holder.txtTitle.setText(ubicacionesTiendas.get(position).getTxtTitle());
        holder.txtUbiTienda.setText(ubicacionesTiendas.get(position).getTxtUbiTienda());
        holder.txtTelefono.setText(ubicacionesTiendas.get(position).getTxtTelefono());

        //holder.foto.setImageResource(listaProductos.get(position).getImageid());
        //holder.fotoTienda.setImageResource(ubicacionesTiendas.get(position).getFotoTienda());
    }

    @Override
    public void onViewAttachedToWindow(ProductosViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animateCircularReveal(holder.itemView);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animaton = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animaton.start();
    }


    @Override
    public int getItemCount() {
        return ubicacionesTiendas.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtUbiTienda, txtTelefono;
        //ImageView fotoTienda;

        public ProductosViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtUbiTienda = itemView.findViewById(R.id.txtUbiTienda);
            txtTelefono = itemView.findViewById(R.id.txtTelefono);
            //fotoTienda=itemView.findViewById(R.id.fotoUbicacion);
        }
    }
}
