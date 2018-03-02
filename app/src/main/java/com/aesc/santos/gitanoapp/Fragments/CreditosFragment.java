package com.aesc.santos.gitanoapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aesc.santos.gitanoapp.Adaptadores.CreditosAdapt;
import com.aesc.santos.gitanoapp.Entidades.Creditos;
import com.aesc.santos.gitanoapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreditosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreditosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreditosFragment extends Fragment {

    private final String nombre_us[] = {
            "Armando Santos",
            "Ricardo Rodríguez",
            "Daniel de Jesús",
            "Joshua Váley",
            "Rafasky Manuel",
            "Erick Ruiz"

    };

    private final String url_photo[] = {
            "http://adrax.hol.es/fotos_programadores/IMG_20171231_071822_639.jpg",
            "http://adrax.hol.es/fotos_programadores/2018-03-01%2002.24.02%201.jpg",
            "http://adrax.hol.es/fotos_programadores/DF7k3gdXkAIoi4N.jpg",
            "http://adrax.hol.es/fotos_programadores/fotojoshua.PNG",
            "http://adrax.hol.es/fotos_programadores/2018-03-01%2002.21.59%201.jpg",
            "http://adrax.hol.es/fotos_programadores/IMG_20180106_192427_955.jpg"
    };

    private final String puestos[] = {
            "Desarrollador y Diseñador",
            "Desarrollador y Diseñador",
            "Desarrollador",
            "Desarrollador",
            "Desarrollador",
            "Instructor"
    };


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Creditos> creditosArrayList;
    RecyclerView rvAboutUs;

    private OnFragmentInteractionListener mListener;

    public CreditosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreditosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreditosFragment newInstance(String param1, String param2) {
        CreditosFragment fragment = new CreditosFragment();
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
        View view = inflater.inflate(R.layout.fragment_creditos, container, false);
        creditosArrayList = new ArrayList<>();

        rvAboutUs = view.findViewById(R.id.rvabout);
        rvAboutUs.setLayoutManager(new GridLayoutManager(getContext(),2));


        final ArrayList creditos = prepareData();
        CreditosAdapt creditosAdapt = new CreditosAdapt(getContext(), creditos);
        rvAboutUs.setAdapter(creditosAdapt);

        return view;
    }

    private ArrayList prepareData() {
        ArrayList creditos = new ArrayList<>();
        for (int i = 0; i < nombre_us.length; i++) {
            Creditos aboutUs = new Creditos();
            aboutUs.setNombre(nombre_us[i]);
            aboutUs.setUrl_perfil(url_photo[i]);
            aboutUs.setPuestos(puestos[i]);
            creditos.add(aboutUs);

        }
        return creditos;
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
