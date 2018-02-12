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
import com.aesc.santos.gitanoapp.R;

import java.util.ArrayList;

/**
 * Created by Android on 2/5/2018.
 */

public class AdaptadorDetalle extends RecyclerView.Adapter<AdaptadorDetalle.ProductosViewHolder> implements View.OnClickListener {

    ArrayList<ProductosVo> listaProductos;
    private View.OnClickListener listener;

    public AdaptadorDetalle(ArrayList<ProductosVo> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_produtos_detalle, parent,false);
        view.setOnClickListener(this);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder holder, int position) {

        holder.txtNombre.setText(listaProductos.get(position).getNombre());
        holder.txtDescripcion.setText(listaProductos.get(position).getDescripcion());
        holder.foto.setImageResource(listaProductos.get(position).getImageid());
    }

    @Override
    public void onViewAttachedToWindow(ProductosViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            animateCircularReveal(holder.itemView);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateCircularReveal(View view){
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animaton = ViewAnimationUtils.createCircularReveal(view,centerX,centerY,startRadius,endRadius);
        view.setVisibility(View.VISIBLE);
        animaton.start();
    }


    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!= null){
            listener.onClick(view);
        }
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtDescripcion;
        ImageView foto;

        public ProductosViewHolder(View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.tvNombre);
            txtDescripcion = itemView.findViewById(R.id.tvDescripcion);
            foto = itemView.findViewById(R.id.idImage);
        }
    }
}
