package com.aesc.santos.gitanoapp.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aesc.santos.gitanoapp.Adaptadores.CategoriasProducto;
import com.aesc.santos.gitanoapp.Adaptadores.CategoriasProductoDetalle;
import com.aesc.santos.gitanoapp.Adaptadores.PromocionesAdapter;
import com.aesc.santos.gitanoapp.Entidades.AndroidVersion;
import com.aesc.santos.gitanoapp.Entidades.ProductosVo;
import com.aesc.santos.gitanoapp.Entidades.Promociones;
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
 * {@link PromocionesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PromocionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PromocionesFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<Promociones> listCategorias;
    RecyclerView recyclerProductos;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PromocionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PromocionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PromocionesFragment newInstance(String param1, String param2) {
        PromocionesFragment fragment = new PromocionesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utilidades.visualizacion=Utilidades.GRID;

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_promociones, container, false);

        listCategorias = new ArrayList<>();

        recyclerProductos = view.findViewById(R.id.idRecycler);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerProductos.setHasFixedSize(true);

        //verificacion = view.findViewById(R.id.imgSinConeccion);
        //verificacion.setVisibility(View.INVISIBLE);

        request = Volley.newRequestQueue(getContext());

        cargarWebService();

        /*ConnectivityManager con = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            verificacion.setVisibility(View.INVISIBLE);
            cargarWebService();
        }else{
            verificacion.setVisibility(View.VISIBLE);
            Toast.makeText(activity, "No se pudo conectar, verifique el acceso a Internet e intente nuevamente", Toast.LENGTH_LONG).show();
        }*/


        return view;
    }

    private void cargarWebService() {
        String url = "http://adrax.hol.es/gitaneAlistaPromociones.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se pudo conectar " + error.toString(), Toast.LENGTH_SHORT).show();
        System.out.println();
        //Log.d(TAG, "onErrorResponse: " + error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Promociones promociones = null;

        JSONArray json = response.optJSONArray("datos");

        try {
            for (int i = 0; i < json.length(); i++){
                promociones = new Promociones();
                JSONObject jsonObject = null;

                jsonObject = json.getJSONObject(i);

                promociones.setNombreP(jsonObject.optString("nombre"));
                promociones.setDescP(jsonObject.optString("descripcion"));
                promociones.setFechaInP(jsonObject.optString("fecha_inicio"));
                promociones.setFechaCaP(jsonObject.optString("fecha_final"));
                promociones.setImgP(jsonObject.optString("foto"));

                listCategorias.add(promociones);
            }

            PromocionesAdapter adapter = new PromocionesAdapter(getContext(),listCategorias);
            recyclerProductos.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error al momento de cargar las Categorias.", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
