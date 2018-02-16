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

        /*CategoriasProductoDetalle adapter = new CategoriasProductoDetalle(listaProductos);
        recyclerProductos.setAdapter(adapter);*/

        return view;
    }

    private void cargarWebService(int key) {
        String url = "http://adrax.hol.es/gitaneAlistaProductosDetalles.php?id=" + key;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "No se pudo conectar " + error.toString(), Toast.LENGTH_SHORT).show();
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

            CategoriasProductoDetalle adapter = new CategoriasProductoDetalle(getContext(),listaProductos);
            recyclerProductos.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error al momento de cargar las Categorias.", Toast.LENGTH_SHORT).show();
        }

    }

   /* private void siSeleccionEsCaliente() {
        listaProductos.add(new ProductosVo(getString(R.string.caliente_americano),getString(R.string.desp_americano),R.drawable.c_americano));
        listaProductos.add(new ProductosVo(getString(R.string.caliente_cappuccino),getString(R.string.desp_cappuccino),R.drawable.c_cappuccino));
        listaProductos.add(new ProductosVo(getString(R.string.caliente_caramel_latte),getString(R.string.desp_caramel_latte),R.drawable.c_caramel_latte));
        listaProductos.add(new ProductosVo(getString(R.string.caliente_chocolate),getString(R.string.desp_chocolate),R.drawable.c_chocolate));
        listaProductos.add(new ProductosVo(getString(R.string.caliente_cochaccino),getString(R.string.desp_cochaccino),R.drawable.c_cochaccino));
        listaProductos.add(new ProductosVo(getString(R.string.caliente_latte),getString(R.string.desp_latte),R.drawable.c_latte));
        listaProductos.add(new ProductosVo(getString(R.string.caliente_mocha),getString(R.string.desp_mocha),R.drawable.c_mocha));
        listaProductos.add(new ProductosVo(getString(R.string.caliente_te_chai),getString(R.string.desp_te_chai),R.drawable.c_te_chai));

    }

    private void siSeleccionEsFria() {
        listaProductos.add(new ProductosVo(getString(R.string.fria_dream_granita),getString(R.string.desp_dream_granita),R.drawable.f_dream_granita));
        listaProductos.add(new ProductosVo(getString(R.string.fria_granita_cafe),getString(R.string.desp_granita_cafe),R.drawable.f_granita_cafe));
        listaProductos.add(new ProductosVo(getString(R.string.fria_granita_frutas),getString(R.string.desp_granita_frutas),R.drawable.f_granita_frutas));
        listaProductos.add(new ProductosVo(getString(R.string.fria_horchata),getString(R.string.desp_horchata),R.drawable.f_horchata));
        listaProductos.add(new ProductosVo(getString(R.string.fria_jamaica),getString(R.string.desp_jamaica),R.drawable.f_jamaica));
        listaProductos.add(new ProductosVo(getString(R.string.fria_jugo_naranja),getString(R.string.desp_jugo_naranja),R.drawable.f_jugo_naranja));
        listaProductos.add(new ProductosVo(getString(R.string.fria_latte_frio),getString(R.string.desp_latte_frio),R.drawable.f_latte_frio));
        listaProductos.add(new ProductosVo(getString(R.string.fria_tamarindo),getString(R.string.desp_tamarindo),R.drawable.f_tamarindo));
        listaProductos.add(new ProductosVo(getString(R.string.fria_te_frio),getString(R.string.desp_te_frio),R.drawable.f_te_frio));

    }

    private void siSeleccionEsNatural() {
        listaProductos.add(new ProductosVo(getString(R.string.natural_berry_cool),getString(R.string.desc_natural_berry_cool),R.drawable.n_berry_cool));
        listaProductos.add(new ProductosVo(getString(R.string.natural_booster),getString(R.string.desc_natural_booster),R.drawable.n_booster));
        listaProductos.add(new ProductosVo(getString(R.string.natural_early_bird),getString(R.string.desc_natural_early_bird),R.drawable.n_early_bird));
        listaProductos.add(new ProductosVo(getString(R.string.natural_magic),getString(R.string.desc_natural_magic),R.drawable.n_magic));
        listaProductos.add(new ProductosVo(getString(R.string.natural_shine),getString(R.string.desc_natural_shine),R.drawable.n_shine));
        listaProductos.add(new ProductosVo(getString(R.string.natural_tropical),getString(R.string.desc_natural_tropical),R.drawable.n_tropical));
        listaProductos.add(new ProductosVo(getString(R.string.natural_vanilla_ice),getString(R.string.desc_natural_vanilla_ice),R.drawable.n_vanilla_ice));
        listaProductos.add(new ProductosVo(getString(R.string.natural_veggie),getString(R.string.desc_natural_veggie),R.drawable.n_veggie));

    }

    private void siSeleccionEsDesayunos() {
        listaProductos.add(new ProductosVo(getString(R.string.desayuno_bagel),getString(R.string.desc_desayuno_bagel),R.drawable.d_bagel));
        listaProductos.add(new ProductosVo(getString(R.string.desayuno_bocadillo),getString(R.string.desc_desayuno_bocadillo),R.drawable.d_bocadillo));
        listaProductos.add(new ProductosVo(getString(R.string.desayuno_chicken_quesadilla),getString(R.string.desc_desayuno_chicken_quesadilla),R.drawable.d_chicken_quesadilla));
        listaProductos.add(new ProductosVo(getString(R.string.desayuno_latbread),getString(R.string.desc_desayuno_latbread),R.drawable.d_latbread));
        listaProductos.add(new ProductosVo(getString(R.string.desayuno_muffin),getString(R.string.desc_desayuno_muffin),R.drawable.d_muffin));
        listaProductos.add(new ProductosVo(getString(R.string.desayuno_muffin_ranchero),getString(R.string.desc_desayuno_muffin_ranchero),R.drawable.d_muffin_ranchero));
        listaProductos.add(new ProductosVo(getString(R.string.desayuno_torta_gitane),getString(R.string.desc_desayuno_torta_gitane),R.drawable.d_torta_gitane));

    }

    private void siSeleccionEsClasicos() {
        listaProductos.add(new ProductosVo(getString(R.string.clasicos_blt_gitane),getString(R.string.desc_clasicos_blt_gitane),R.drawable.cla_blt_gitane));
        listaProductos.add(new ProductosVo(getString(R.string.clasicos_chicken_cheddar),getString(R.string.desc_clasicos_chicken_cheddar),R.drawable.cla_chicken_cheddar));
        listaProductos.add(new ProductosVo(getString(R.string.clasicos_chicken_chimichuri),getString(R.string.desc_clasicos_chicken_chimichuri),R.drawable.cla_chicken_chimichuri));
        listaProductos.add(new ProductosVo(getString(R.string.clasicos_citano_de_jamon),getString(R.string.desc_clasicos_citano_de_jamon),R.drawable.cla_citano_de_jamon));
        listaProductos.add(new ProductosVo(getString(R.string.clasicos_citano_de_pollo),getString(R.string.desc_clasicos_citano_de_pollo),R.drawable.cla_citano_de_pollo));
        listaProductos.add(new ProductosVo(getString(R.string.clasicos_croissant),getString(R.string.desc_clasicos_croissant),R.drawable.cla_croissant));
        listaProductos.add(new ProductosVo(getString(R.string.clasicos_sandwich_caprece),getString(R.string.desc_clasicos_sandwich_caprece),R.drawable.cla_sandwich_caprece));

    }

    private void siSeleccionEsHealthy() {
        listaProductos.add(new ProductosVo(getString(R.string.healthy_cesar_wrap),getString(R.string.desc_healthy_cesar_wrap),R.drawable.h_cesar_wrap));
        listaProductos.add(new ProductosVo(getString(R.string.healthy_ensalada_cesar),getString(R.string.desc_healthy_ensalada_cesar),R.drawable.h_ensalada_cesar));
        listaProductos.add(new ProductosVo(getString(R.string.healthy_emsalada_chicken_mustrad),getString(R.string.desc_healthy_emsalada_chicken_mustrad),R.drawable.h_ensalada_chicken_mustrad));
        listaProductos.add(new ProductosVo(getString(R.string.healthy_ensalada_veggie),getString(R.string.desc_healthy_ensalada_veggie),R.drawable.h_ensalada_veggie));
        listaProductos.add(new ProductosVo(getString(R.string.healthy_ensalada_verano),getString(R.string.desc_healthy_ensalada_verano),R.drawable.h_ensalada_verano));
        listaProductos.add(new ProductosVo(getString(R.string.healthy_sandwich_jardinero),getString(R.string.desc_healthy_sandwich_jardinero),R.drawable.h_sandwich_jardinero));
        listaProductos.add(new ProductosVo(getString(R.string.healthy_sandwinh_vegetariano),getString(R.string.desc_healthy_sandwinh_vegetariano),R.drawable.h_sandwich_vegetariano));

    }

    private void siSeleccionEsPostres() {
        listaProductos.add(new ProductosVo(getString(R.string.postres_brazo_gitano),getString(R.string.desc_postres_brazo_gitano),R.drawable.p_brazo_gitano));
        listaProductos.add(new ProductosVo(getString(R.string.postres_brownie),getString(R.string.desc_postres_brownie),R.drawable.p_brownie));
        listaProductos.add(new ProductosVo(getString(R.string.postres_galletas),getString(R.string.desc_postres_galletas),R.drawable.p_galletas));
        listaProductos.add(new ProductosVo(getString(R.string.postres_gipsy),getString(R.string.desc_postres_gipsy),R.drawable.p_gipsy));
        listaProductos.add(new ProductosVo(getString(R.string.postres_naked_cake_ch),getString(R.string.desc_postres_naked_cake_ch),R.drawable.p_naked_cake_ch));
        listaProductos.add(new ProductosVo(getString(R.string.postres_pastel_de_moka),getString(R.string.desc_postres_pastel_de_moka),R.drawable.p_pastel_de_moka));
        listaProductos.add(new ProductosVo(getString(R.string.postres_pie_de_queso),getString(R.string.desc_postres_pie_de_queso),R.drawable.p_pie_de_queso));
        listaProductos.add(new ProductosVo(getString(R.string.postres_zepelin),getString(R.string.desc_postres_zepelin),R.drawable.p_zepelines));

    }*/

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
