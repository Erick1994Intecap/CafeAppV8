package com.aesc.santos.gitanoapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aesc.santos.gitanoapp.Adaptadores.CategoriasProducto;
import com.aesc.santos.gitanoapp.Entidades.AndroidVersion;
import com.aesc.santos.gitanoapp.Intefaces.IComunicaFragments;
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
 * {@link ProductosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductosFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {

    private final String android_image_urls[] = {
            "http://adrax.hol.es/img_caffe/Bebidas%20Calientes/Americano.png",
            "http://adrax.hol.es/img_caffe/Bebidas%20Frias/Dream%20Granita.png",
            "http://adrax.hol.es/img_caffe/Naturals/Booster.png",
            "http://adrax.hol.es/img_caffe/Desayunos/Bocadillo.png",
            "http://adrax.hol.es/img_caffe/Cla%CC%81sicos/Croissant.png",
            "http://adrax.hol.es/img_caffe/Healthy/Ce%CC%81sar%20Wrap.png",
            "http://adrax.hol.es/img_caffe/Postres/Galletas.png"
    };
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "ProductosFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ImageView verificacion;
    ArrayList<AndroidVersion> listCategorias;
    RecyclerView recyclerProductos;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    Activity activity;
    IComunicaFragments interfaceComunicaFragments;

    public ProductosFragment() {
        // Required empty public constructor
    }

    public static ProductosFragment newInstance(String param1, String param2) {
        ProductosFragment fragment = new ProductosFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        listCategorias = new ArrayList<>();
        
        recyclerProductos = view.findViewById(R.id.recyclerid);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerProductos.setHasFixedSize(true);

        verificacion = view.findViewById(R.id.imgSinConeccion);
        verificacion.setVisibility(View.INVISIBLE);

        request = Volley.newRequestQueue(getContext());

        ConnectivityManager con = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            verificacion.setVisibility(View.INVISIBLE);
            cargarWebService();
        }else{
            verificacion.setVisibility(View.VISIBLE);
            Toast.makeText(activity, "No se pudo conectar, verifique el acceso a Internet e intente nuevamente", Toast.LENGTH_LONG).show();
        }

        /*final ArrayList listCategorias = prepareData();
        CategoriasProducto adapter = new CategoriasProducto(getContext(),listCategorias);
        recyclerProductos.setAdapter(adapter);


        //AdaptadorProductos adapter = new AdaptadorProductos(listaProductos);

    recyclerProductos.setAdapter(adapter);
    adapter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            interfaceComunicaFragments.enviarProducto(recyclerProductos.getChildAdapterPosition(view));
            //Toast.makeText(getContext(), "Selecc"+listCategorias.get(recyclerProductos.getChildAdapterPosition(view)), Toast.LENGTH_SHORT).show();
        }
    });*/

        return view;
    }

    private void cargarWebService() {
        String url = "http://192.168.22.37/gitane/wsJSONConsultarLista.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(activity, "No se pudo conectar " + error.toString(), Toast.LENGTH_SHORT).show();
        System.out.println();
        Log.d(TAG, "onErrorResponse: " + error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        AndroidVersion categoria = null;

        JSONArray json = response.optJSONArray("datos");

        try {
            for (int i = 0; i< json.length(); i++){
                categoria = new AndroidVersion();
                JSONObject jsonObject = null;

                jsonObject = json.getJSONObject(i);

                categoria.setAndroid_version_name(jsonObject.optString("name"));
                categoria.setAndroid_image_url(android_image_urls[i]);

                listCategorias.add(categoria);
            }

            CategoriasProducto adapter = new CategoriasProducto(getContext(),listCategorias);
            recyclerProductos.setAdapter(adapter);

            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfaceComunicaFragments.enviarProducto(recyclerProductos.getChildAdapterPosition(view));
                    //Toast.makeText(getContext(), "Selecc"+listCategorias.get(recyclerProductos.getChildAdapterPosition(view)), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(activity, "Error al momento de cargar las Categorias.", Toast.LENGTH_SHORT).show();
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

        if (context instanceof Activity){
            this.activity = (Activity) context;
            interfaceComunicaFragments = (IComunicaFragments) this.activity;
        }

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
