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

import com.aesc.santos.gitanoapp.Adaptadores.AdaptadorTienda;
import com.aesc.santos.gitanoapp.Entidades.Tiendaszona;
import com.aesc.santos.gitanoapp.Intefaces.IComunicaFragments;
import com.aesc.santos.gitanoapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TiendaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TiendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TiendaFragment extends Fragment {
    private final String tiendaszonasZ[] = {
            "Zona 1",
            "Zona 3",
            "Zona 4",
            "Zona 5",
            "Zona 7",
            "Zona 9",
            "Zona 10",
            "Zona 11",
            "Zona 12",
            "Zona 13",
            "Zona 14",
            "Zona 15",
            "Zona 16",
            "Zona 17"


    };
    ArrayList<Tiendaszona> tiendaszonas;
    private RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "TiendaFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    IComunicaFragments interfaceComunicaFragments;
    Activity activity;

    public TiendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TiendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TiendaFragment newInstance(String param1, String param2) {
        TiendaFragment fragment = new TiendaFragment();
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
        View view = inflater.inflate(R.layout.fragment_tienda, container, false);
        tiendaszonas = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerTienda);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final ArrayList tiendaszonas = prepareData();
        AdaptadorTienda adapter = new AdaptadorTienda(getContext(), tiendaszonas);
        recyclerView.setAdapter(adapter);



        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceComunicaFragments.enviarZonas(recyclerView.getChildAdapterPosition(view));
            }
        });
        return view;
    }

    private ArrayList prepareData() {
        ArrayList tiendaszonas = new ArrayList<>();
        for (int i = 0; i < tiendaszonasZ.length; i++) {
            Tiendaszona tiendaszonac = new Tiendaszona();
            tiendaszonac.setZona(tiendaszonasZ[i]);
            tiendaszonas.add(tiendaszonac);
        }
        return tiendaszonas;
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

        if (context instanceof Activity) {
            this.activity = (Activity) context;
            interfaceComunicaFragments = (IComunicaFragments) this.activity;
            //mListener = (OnFragmentInteractionListener) context;
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
