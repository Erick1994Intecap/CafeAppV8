package com.aesc.santos.gitanoapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//import com.aesc.santos.gitanoapp.Adaptadores.AdaptadorProductos;
import com.aesc.santos.gitanoapp.Adaptadores.DataAdapter;
import com.aesc.santos.gitanoapp.Entidades.AndroidVersion;
import com.aesc.santos.gitanoapp.Entidades.ProductosVo;
import com.aesc.santos.gitanoapp.Intefaces.IComunicaFragments;
import com.aesc.santos.gitanoapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductosFragment extends Fragment {
    private final String android_version_names[] = {
            "Bebidas Calientes",
            "Bebidas Frías",
            "Bebidas Naturales",
            "Desayunos",
            "Clásicos",
            "Healthy",
            "Postres"
    };

    private final String android_image_urls[] = {
            "http://adrax.hol.es/img_caffe/Bebidas%20Calientes/Americano.png",
            "http://adrax.hol.es/img_caffe/Bebidas%20Frias/Dream%20Granita.png",
            "http://adrax.hol.es/img_caffe/Cla%CC%81sicos/Croissant.png",
            "http://adrax.hol.es/img_caffe/Desayunos/Bocadillo.png",
            "http://adrax.hol.es/img_caffe/Healthy/Ce%CC%81sar%20Wrap.png",
            "http://adrax.hol.es/img_caffe/Naturals/Booster.png",
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

    ArrayList<AndroidVersion> androidVersions;
    RecyclerView recyclerProductos;

    Activity activity;
    IComunicaFragments interfaceComunicaFragments;

    public ProductosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductosFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        androidVersions = new ArrayList<>();
        recyclerProductos = view.findViewById(R.id.recyclerid);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(getContext()));

        final ArrayList androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getContext(),androidVersions);
        recyclerProductos.setAdapter(adapter);


        //AdaptadorProductos adapter = new AdaptadorProductos(listaProductos);

    recyclerProductos.setAdapter(adapter);
    adapter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            interfaceComunicaFragments.enviarProducto(recyclerProductos.getChildAdapterPosition(view));
            //Toast.makeText(getContext(), "Selecc"+androidVersions.get(recyclerProductos.getChildAdapterPosition(view)), Toast.LENGTH_SHORT).show();
        }
    });

        return view;
    }

    private ArrayList prepareData() {

        ArrayList android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }

   /* private void llenarListaProductos() {
        listaProductos.add(new ProductosVo(getString(R.string.producto_nombre_caliente),getString(R.string.producto_desp_caliente),R.drawable.caliente));
        listaProductos.add(new ProductosVo(getString(R.string.producto_nombre_fria),getString(R.string.producto_desp_fria),R.drawable.fria));
        listaProductos.add(new ProductosVo(getString(R.string.producto_nombre_naturales),getString(R.string.producto_desp_naturales),R.drawable.natural));
        listaProductos.add(new ProductosVo(getString(R.string.producto_nombre_desayuno),getString(R.string.producto_desp_desayuno),R.drawable.desayuno));
        listaProductos.add(new ProductosVo(getString(R.string.producto_nombre_clasico),getString(R.string.producto_desp_clasico),R.drawable.clasica));
        listaProductos.add(new ProductosVo(getString(R.string.producto_nombre_healthy),getString(R.string.producto_desp_healthy),R.drawable.healthy));
        listaProductos.add(new ProductosVo(getString(R.string.producto_nombre_postre),getString(R.string.producto_desp_postre),R.drawable.postre));

    }*/

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
