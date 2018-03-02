package com.aesc.santos.gitanoapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aesc.santos.gitanoapp.Entidades.VolleySingleton;
import com.aesc.santos.gitanoapp.LoginActivity;
import com.aesc.santos.gitanoapp.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class RegistrarFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    StringRequest stringRequest;
    EditText dpi, nombre, apellido, fecha, correo, telefono, pass;
    Button guardar;
    private ImageView mImagenLogo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegistrarFragment() {
        // Required empty public constructor
    }


    public static RegistrarFragment newInstance(String param1, String param2) {
        RegistrarFragment fragment = new RegistrarFragment();
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
        View view = inflater.inflate(R.layout.fragment_registrar, container, false);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        dpi = view.findViewById(R.id.txtDpi);
        nombre = view.findViewById(R.id.txtNombre);
        apellido = view.findViewById(R.id.txtApellido);
        fecha = view.findViewById(R.id.txtFechaNac);
        correo = view.findViewById(R.id.txtCorreo);
        telefono = view.findViewById(R.id.txtTelefono);
        pass = view.findViewById(R.id.txtContaseña);
        mImagenLogo = view.findViewById(R.id.logoGitaneRegistrar);

        Picasso.with(getContext()).load(R.drawable.logo).resize(300,100).error(R.mipmap.ic_launcher).into(mImagenLogo);
        guardar = view.findViewById(R.id.btnGuardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((dpi.length() != 0) && (nombre.length() != 0) && (apellido.length() != 0) && (fecha.length() != 0) && (correo.length() != 0) && (telefono.length() != 0) && (pass.length() != 0)){
                    cargarWebService();
                }else {
                    Toast.makeText(getActivity(), "Datos incompletos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void limpiar(){
        dpi.setText("");
        nombre.setText("");
        apellido.setText("");
        fecha.setText("");
        correo.setText("");
        telefono.setText("");
    }
    // MI WEBSEVICE
    private void cargarWebService() {

        String url = "http://adrax.hol.es/php/registro.php?";

        stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //ESTO RECIBE LA RESPUESTA DEL WEBSERVICE CUANDO TODO ESTA CORRECTO
            @Override
            public void onResponse(String response) {

                if (response.trim().equalsIgnoreCase("registra")){
                    limpiar();
                    Toast.makeText(getContext(), "Sea Registrado con exito!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            //Y ESTA PARTE SE EJECUTA CUANDO DA ALGUN ERROR
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se ah podido conectar", Toast.LENGTH_SHORT).show();

            }
        }){
            //ESTO NOS VA A PERMITIR ENVIAR LOS DATOS MEDIANTE POST A NUESTRO WEBSERVICE
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //String sDocumento = documento.getText().toString();

                String edpi =  dpi.getText().toString();
                String enombre =  nombre.getText().toString();
                String eapellido =  apellido.getText().toString();
                String econtraseña =  pass.getText().toString();
                String efecha =  fecha.getText().toString();
                String ecorre =  correo.getText().toString();
                String etelefono =  telefono.getText().toString();

                Map<String,String> parametros = new HashMap<>();
                //parametros.put("documento",sDocumento);
                parametros.put("dpi",edpi);
                parametros.put("nombre",enombre);
                parametros.put("apellido",eapellido);
                parametros.put("password",econtraseña);
                parametros.put("fecha_de_nacimiento",efecha);
                parametros.put("correo",ecorre);
                parametros.put("telefono",etelefono);

                return parametros;
            }
        };
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(stringRequest);
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
