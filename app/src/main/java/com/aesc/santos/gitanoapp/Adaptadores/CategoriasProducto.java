package com.aesc.santos.gitanoapp.Adaptadores;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aesc.santos.gitanoapp.Entidades.AndroidVersion;
import com.aesc.santos.gitanoapp.R;
import com.aesc.santos.gitanoapp.Utilidades.Utilidades;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Android on 2/5/2018.
 */

public class CategoriasProducto extends RecyclerView.Adapter<CategoriasProducto.ProductosViewHolder> implements View.OnClickListener {

    private ArrayList<AndroidVersion> android_versions;
    private Context context;
    private View.OnClickListener listener;

    public CategoriasProducto(Context context, ArrayList<AndroidVersion> android_versions) {
        this.context = context;
        this.android_versions = android_versions;
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layout = 0;

        if (Utilidades.visualizacion==Utilidades.LIST){
            //Toast.makeText(context, "LISt", Toast.LENGTH_SHORT).show();
            layout = R.layout.list_productos_categoria;
        }else{
            //Toast.makeText(context, "Grid", Toast.LENGTH_SHORT).show();
            layout = R.layout.grid_promociones;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        view.setOnClickListener(this);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder holder, int position) {
        holder.txtDescripcion.setText(android_versions.get(position).getAndroid_version_name().toString());

        //piccaso inplementado
        Picasso.with(context).load(android_versions.get(position).getAndroid_image_url()).fit().error(R.mipmap.ic_launcher).into(holder.foto);

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
        return android_versions.size();
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

        TextView txtNombre, txtDescripcion;
        ImageView foto;

        public ProductosViewHolder(View itemView) {
            super(itemView);

            //txtNombre = itemView.findViewById(R.id.tvNombre);
            txtDescripcion = itemView.findViewById(R.id.tvDescripcion);
            foto = itemView.findViewById(R.id.idImage);
        }
    }
}
