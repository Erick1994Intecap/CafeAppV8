package com.aesc.santos.gitanoapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aesc.santos.gitanoapp.BodyActivity;
import com.aesc.santos.gitanoapp.Entidades.Usuario;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "LoginFragment";
    private EditText txtUsuario, txtPassword;
    private Button btnIngresar;

    RequestQueue mRequestQueue;
    JsonObjectRequest mJsonObject;
    TextView btnRegistrar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtUsuario = view.findViewById(R.id.etUsername);
        txtPassword =  view.findViewById(R.id.etPassword);
        btnIngresar = view.findViewById(R.id.btnLogin);
        btnRegistrar = view.findViewById(R.id.registrar);



        mRequestQueue = Volley.newRequestQueue(getContext());

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUsuario.length() != 0){
                    initCesion();
                }else{
                    Toast.makeText(getContext(), "Es necesario llenar lo", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarFragment mRegistrarFragment = new RegistrarFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, mRegistrarFragment).addToBackStack(null).commit();
            }
        });


        return view;
    }

    private void initCesion() {

            String url = "http://adrax.hol.es/php/consultarusuario.php?DPI=" + txtUsuario.getText().toString() + "&password=" + txtPassword.getText().toString();

            mJsonObject = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
            mRequestQueue.add(mJsonObject);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error al iniciar secion", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Usuario user = new Usuario();
        JSONArray mJsonArray = response.optJSONArray("datos");
        JSONObject mJsonObject = null;

        try {
            mJsonObject = mJsonArray.getJSONObject(0);

            user.setDPI(mJsonObject.optString("DPI"));
            user.setNombre(mJsonObject.optString("nombre"));
            user.setApellido(mJsonObject.optString("apellido"));
            user.setPuntos(mJsonObject.optInt("puntos"));

            Context mContext = getContext();
            SharedPreferences mPreferences = getContext().getSharedPreferences("ArchivoSP", mContext.MODE_PRIVATE);
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString("MiDato", user.getNombre());
            editor.commit();

            SharedPreferences mPreferencesApellido = getContext().getSharedPreferences("DatoA",mContext.MODE_PRIVATE);
            SharedPreferences.Editor mEditor1 = mPreferencesApellido.edit();
            mEditor1.putString("MiDato2", user.getApellido());
            mEditor1.commit();

            SharedPreferences mPreferencesDPI = getContext().getSharedPreferences("DatoDPI",mContext.MODE_PRIVATE);
            SharedPreferences.Editor mEditorDPI = mPreferencesDPI.edit();
            mEditorDPI.putString("DPI", user.getDPI());
            mEditorDPI.commit();

            SharedPreferences mPreferencesScore = getContext().getSharedPreferences("DatoScore",mContext.MODE_PRIVATE);
            SharedPreferences.Editor mEditorScore = mPreferencesScore.edit();
            mEditorScore.putInt("score", user.getPuntos());
            mEditorScore.commit();

            //editor.putInt("score",user.getPuntos());
            Intent intent = new Intent(getActivity(), BodyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getActivity().startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
