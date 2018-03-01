package com.aesc.santos.gitanoapp.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aesc.santos.gitanoapp.Entidades.Creditos;
import com.aesc.santos.gitanoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Android on 27/02/2018.
 */

public class CreditosAdapt extends RecyclerView.Adapter<CreditosAdapt.AboutHolder> implements View.OnClickListener {
    private ArrayList<Creditos> creditosArrayList;
    private Context context;
    private View.OnClickListener listener;

    public CreditosAdapt(Context context, ArrayList<Creditos> creditosArrayList) {
        this.context = context;
        this.creditosArrayList = creditosArrayList;
    }

    @Override
    public AboutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_creditos, parent, false);
        view.setOnClickListener(this);

        return new AboutHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutHolder holder, int position) {
        holder.txtAbtUs.setText(creditosArrayList.get(position).getNombre().toString());
        holder.txtPuesto.setText(creditosArrayList.get(position).getPuestos().toString());
        Picasso.with(context).load(creditosArrayList.get(position).getUrl_perfil()).fit().error(R.mipmap.ic_launcher).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return creditosArrayList.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class AboutHolder extends RecyclerView.ViewHolder {
        TextView txtAbtUs;
        CircleImageView photo;
        TextView txtPuesto;

        public AboutHolder(View itemView) {
            super(itemView);
            txtAbtUs = itemView.findViewById(R.id.txtNombreAbtUs);
            photo = itemView.findViewById(R.id.crPhoto);
            txtPuesto=itemView.findViewById(R.id.txtPuesto);
        }
    }
}
