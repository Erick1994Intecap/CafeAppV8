package com.aesc.santos.gitanoapp.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aesc.santos.gitanoapp.Entidades.Promociones;
import com.aesc.santos.gitanoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Android on 2/28/2018.
 */

public class PromocionesAdapter extends RecyclerView.Adapter<PromocionesAdapter.ViewHolder>{

    private ArrayList<Promociones> mPromociones = new ArrayList<>();
    Context mContext;

    public PromocionesAdapter(Context mContext, ArrayList<Promociones> mPromociones) {
        this.mPromociones = mPromociones;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_promociones,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(mContext).load(mPromociones.get(position).getImgP()).fit().error(R.mipmap.ic_launcher).into(holder.vistaPrevia);
        holder.titulo.setText(mPromociones.get(position).getNombreP().toString());
        holder.descripcion.setText(mPromociones.get(position).getDescP());
        holder.fechas.setText(mPromociones.get(position).getFechaInP().toString() + " a " + mPromociones.get(position).getFechaCaP().toString());
    }

    @Override
    public int getItemCount() {
        return mPromociones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView vistaPrevia;
        TextView titulo, descripcion, fechas;

        public ViewHolder(View itemView) {
            super(itemView);

            vistaPrevia = itemView.findViewById(R.id.idImage);
            titulo = itemView.findViewById(R.id.tvDescripcion);
            descripcion = itemView.findViewById(R.id.tvDescripcionReal);
            fechas = itemView.findViewById(R.id.tvFechas);
        }
    }
}
