package com.aesc.santos.gitanoapp.Fragments;

import android.app.Activity;
import android.content.Context;
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

import com.aesc.santos.gitanoapp.Adaptadores.AdaptadorTienda;
import com.aesc.santos.gitanoapp.Adaptadores.AdaptadorTiendaDetalle;
import com.aesc.santos.gitanoapp.Entidades.ProductosVo;
import com.aesc.santos.gitanoapp.Entidades.UbicacionesTienda;
import com.aesc.santos.gitanoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TiendaDetalleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TiendaDetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TiendaDetalleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "TiendaDetalleFragment";
    private int position;
    ImageView fotoUbicacion;
    ArrayList<UbicacionesTienda> ubicacionesTiendas;
    RecyclerView recyclerTienda;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TiendaDetalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TiendaDetalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TiendaDetalleFragment newInstance(String param1, String param2) {
        TiendaDetalleFragment fragment = new TiendaDetalleFragment();
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
            position = getArguments().getInt("key", 0);
            //Toast.makeText(getContext(), "eshta" + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tienda_detalle, container, false);
        Log.d(TAG, "onCreateView: ESTIY AQYU");
        //fotoUbicacion = view.findViewById(R.id.fotoUbicacion);


        ubicacionesTiendas = new ArrayList<>();
        recyclerTienda = view.findViewById(R.id.recyclerTiendaDetalle);
        recyclerTienda.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (position == 0) {
            Log.d(TAG, "onCreateView: Zona 1.");
            seleccionZona1();
        } else if (position == 1) {
            Log.d(TAG, "Zona 3");
            seleccionZona3();
        } else if (position == 2) {
            Log.d(TAG, "Zona 4");
            seleccionZona4();
        } else if (position == 3) {
            Log.d(TAG, "Zona 6");
            seleccionZona6();
        } else if (position == 4) {
            Log.d(TAG, "Zona 7");
            seleccionZona7();
        } else if (position == 5) {
            Log.d(TAG, "Zona 9");
            seleccionZona9();
        } else if (position == 6) {
            Log.d(TAG, "Zona 10");
            seleccionZona10();
        } else if (position == 7) {
            Log.d(TAG, "Zona 11");
            seleccionZona11();
        } else if (position == 8) {
            Log.d(TAG, "Zona 12");
            seleccionZona12();
        } else if (position == 9) {
            Log.d(TAG, "Zona 13");
            seleccionZona13();
        } else if (position == 10) {
            Log.d(TAG, "Zona 14");
            seleccionZona14();
        } else if (position == 11) {
            Log.d(TAG, "Zona 15");
            seleccionZona15();
        } else if (position == 12) {
            Log.d(TAG, "Zona 16");
            seleccionZona16();

        } else if (position == 13) {
            Log.d(TAG, "Zona 17");
            seleccionZona17();
        } else if (position == 14) {
            Log.d(TAG, "Extrerior");
            Exterior();
        }
        AdaptadorTiendaDetalle adapters = new AdaptadorTiendaDetalle(ubicacionesTiendas);
        recyclerTienda.setAdapter(adapters);

        return view;
    }

    private void Exterior() {
        //ubicacionesTiendas.add(new UbicacionesTienda("", "", "Tel: "));
        ubicacionesTiendas.add(new UbicacionesTienda("Comercial El Frutal", "Boulevard El Frutal 14-00 local No. 38", "Tel: 4032-9171"));

    }

    private void seleccionZona9() {

        ubicacionesTiendas.add(new UbicacionesTienda("XEROX", "6 Avenida A 9-24 Plaza Rabi", "Tel: 4038-4927"));

        // Picasso.with(getContext()).load("https://cdn-pro.elsalvador.com/wp-content/uploads/2017/01/30182002/1476896384419.jpg")
        //.into(fotoUbicacion);
    }

    private void seleccionZona7() {
        ubicacionesTiendas.add(new UbicacionesTienda("Álorica 2", "Calzada Roosevelt 14-82 Local 160I", "Tel: 4031-0517"));
        ubicacionesTiendas.add(new UbicacionesTienda("Plaza Madero", "Calzada Roosevelt, Kilometro 13.5, 7-59", "Tel: 4031-0951"));
        ubicacionesTiendas.add(new UbicacionesTienda("Álorica 3", "Calzada Roosevelt 14-82 Local 160J", "Tel: 4032-8400"));
        ubicacionesTiendas.add(new UbicacionesTienda("Galerias Primma", "Calzada Rooselvet 14-82, Planta Baja PB22", "Tel: 4029-8879"));
        ubicacionesTiendas.add(new UbicacionesTienda("Peri-Salida Cemaco", "Calzada Roosevelt 25-50", "Tel: 4033-3791"));
        ubicacionesTiendas.add(new UbicacionesTienda("Peri-Interior Cemaco", "Calzada Roosevelt 25-50 ", "Tel: 4033-4048"));
        ubicacionesTiendas.add(new UbicacionesTienda("Peri Plaza", "Calzada Roosevelt 25-50 Plaza del Sabor No.3", "Tel: 4038-0834"));



    }

    private void seleccionZona10() {
        ubicacionesTiendas.add(new UbicacionesTienda("UFM Medicina", "6ta Avenida 7-55 Interior Hospital La Esperanza", "Tel: 4031-5885"));
        ubicacionesTiendas.add(new UbicacionesTienda("Nearsol", "20 calle 5-25 Edificio Nearsol 4to Nivel", "Tel: 4031-0526"));
        ubicacionesTiendas.add(new UbicacionesTienda("Oakland Mall", "Diagonal 6 13-10 Tercer Nivel, Local FC-13", "Tel: 4053-6290"));
        ubicacionesTiendas.add(new UbicacionesTienda("Plaza Cemaco", "Boulevard Los Próceres 4-96 local 33", "Tel: 4033-6458"));
        ubicacionesTiendas.add(new UbicacionesTienda("UFM Odontología", "Calle Manuel F. Ayau 6 Calle 7-11", "Tel: --"));
        ubicacionesTiendas.add(new UbicacionesTienda("UFM", "6a. Calle Final, 2do Nivel", "Tel: 4038-1290"));

    }

    private void seleccionZona11() {

    }

    private void seleccionZona12() {
    }

    private void seleccionZona13() {
    }

    private void seleccionZona14() {

    }

    private void seleccionZona15() {

    }

    private void seleccionZona17() {
    }

    private void seleccionZona16() {
    }

    private void seleccionZona6() {
        ubicacionesTiendas.add(new UbicacionesTienda("Cementos Progreso", "15 avenida 18-01 Finca la Pradera Casa 48", "Tel: 4033-4798"));
    }

    private void seleccionZona4() {
        ubicacionesTiendas.add(new UbicacionesTienda("Cemaco", "7a. Avenida 2-34, Local 22", "Tel: 4033-7786"));
        ubicacionesTiendas.add(new UbicacionesTienda("Gran Centro Comercial", "6ta. Avenida 0-60, Planta Baja", "Tel: 4033-6695"));
        ubicacionesTiendas.add(new UbicacionesTienda("IGA", "Ruta 1 4-05", "Tel: 4032-5261"));

    }

    private void seleccionZona3() {
    }

    private void seleccionZona1() {
        ubicacionesTiendas.add(new UbicacionesTienda("Zona 1", "6ta. Avenida 8-82", "Tel: 4038-2012"));
        ubicacionesTiendas.add(new UbicacionesTienda("Banco de Guatemala", "7a Avenida 22-01", "Tel: 4032-6413"));

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    /*@Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

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
