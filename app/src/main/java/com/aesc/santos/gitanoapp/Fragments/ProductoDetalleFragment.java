package com.aesc.santos.gitanoapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.aesc.santos.gitanoapp.Adaptadores.CategoriasProductoDetalle;
import com.aesc.santos.gitanoapp.Entidades.ProductosVo;
import com.aesc.santos.gitanoapp.R;
import com.aesc.santos.gitanoapp.Utilidades.Utilidades;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductoDetalleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductoDetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductoDetalleFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "ProductoDetalleFragment";
    private int position;

    ArrayList<ProductosVo> listaProductos;
    RecyclerView recyclerProductos;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView ver;
    public ProductoDetalleFragment() {
        // Required empty public constructor
    }

    public static ProductoDetalleFragment newInstance(String param1, String param2) {
        ProductoDetalleFragment fragment = new ProductoDetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("key",0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_producto_detalle, container, false);
        int key = 0;
        //ver = view.findViewById(R.id.hola);
        //ver.setText(String.valueOf(position));
        //Toast.makeText(getContext(), "Ver: " + position, Toast.LENGTH_SHORT).show();

        listaProductos = new ArrayList<>();

        recyclerProductos = view.findViewById(R.id.recyclerProductosSubMenu);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerProductos.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());

        if (position == 0){
            Log.d(TAG, "onCreateView: Comida Caliente.");
            key = 1;

            //siSeleccionEsCaliente();
        }else if (position == 1){
            Log.d(TAG, "onCreateView: Comida Fria");
            key = 2;
            //siSeleccionEsFria();
        }else if (position == 2){
            Log.d(TAG, "onCreateView: Comida Natural");
            key = 3;
            //siSeleccionEsNatural();
        }else if (position == 3){
            Log.d(TAG, "onCreateView: Desayunos");
            key = 4;
            //siSeleccionEsDesayunos();
        }else if (position == 4){
            Log.d(TAG, "onCreateView: Comida Clisica");
            key = 5;
            //siSeleccionEsClasicos();
        }else if (position == 5){
            Log.d(TAG, "onCreateView: Comida Healthy");
            key = 6;
            //siSeleccionEsHealthy();
        }else if (position == 6){
            Log.d(TAG, "onCreateView: Comida Postres");
            key = 7;
            //siSeleccionEsPostres();
        }

        cargarWebService(key);



        return view;
    }

    private void cargarWebService(int key) {
        String url = "http://adrax.hol.es/gitaneAlistaProductosDetalles.php?id=" + key;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "Datos no disponibles", Toast.LENGTH_SHORT).show();
        System.out.println();
        Log.d(TAG, "onErrorResponse: " + error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        ProductosVo producto = null;

        JSONArray json = response.optJSONArray("datos");

        try {
            for (int i = 0; i < json.length(); i++){
                producto = new ProductosVo();
                JSONObject jsonObject = null;

                jsonObject = json.getJSONObject(i);

                producto.setNombre(jsonObject.optString("name"));
                producto.setDescripcion(jsonObject.optString("description"));
                producto.setPrecio(jsonObject.optString("precio"));
                producto.setImage_url(jsonObject.optString("imagen_url"));

                listaProductos.add(producto);
            }

            CategoriasProductoDetalle adapter = new CategoriasProductoDetalle(listaProductos,getContext(), Utilidades.FRAGMENT_PRODUCTO_DETALLE);
            recyclerProductos.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Datos no disponibles", Toast.LENGTH_SHORT).show();
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
