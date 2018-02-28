package com.aesc.santos.gitanoapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aesc.santos.gitanoapp.BodyActivity;
import com.aesc.santos.gitanoapp.Entidades.Usuario;
import com.aesc.santos.gitanoapp.LoginActivity;
import com.aesc.santos.gitanoapp.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PuntosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PuntosFragment";
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    TextView nombre, estrellas, canal;
    ImageView codigoBarras;
    String dpiCod;

    public PuntosFragment() {
        // Required empty public constructor
    }


    public static PuntosFragment newInstance(String param1, String param2) {
        PuntosFragment fragment = new PuntosFragment();
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
        View view = inflater.inflate(R.layout.fragment_puntos, container, false);
        codigoBarras=view.findViewById(R.id.imgCodUser);
        nombre = view.findViewById(R.id.tvNameUser);
        estrellas = view.findViewById(R.id.tvTotalEstrellas);
        canal = view.findViewById(R.id.nombre_del_tema);

        SharedPreferences sharedPred = getContext().getSharedPreferences("ArchivoSP",getContext().MODE_PRIVATE);
        String nombreUser = sharedPred.getString("MiDato","Error");

        //canal.setText(nombreUser);

        SharedPreferences mPreferences = getContext().getSharedPreferences("DatoA",getContext().MODE_PRIVATE);
        String apellidoSP = mPreferences.getString("MiDato2", "Error");

        SharedPreferences mPreferencesDPI = getContext().getSharedPreferences("DatoDPI",getContext().MODE_PRIVATE);
        long valDPI = mPreferencesDPI.getLong("DPI", 0);
        dpiCod = String.valueOf(valDPI);

        //Toast.makeText(getContext(), String.valueOf(valDPI), Toast.LENGTH_SHORT).show();

        generarBarcode();

        SharedPreferences mPreferencesScore = getContext().getSharedPreferences("DatoScore",getContext().MODE_PRIVATE);
        int valScore = mPreferencesScore.getInt("score", 35968745);
        String puntosV = String.valueOf(valScore);

        //Toast.makeText(getContext(), "DPI: " + valDPI, Toast.LENGTH_SHORT).show();

        nombre.setText(nombreUser + " " + apellidoSP.toString());
        estrellas.setText(puntosV.toString() + " Estrellas");

        return view;
    }

    public void generarBarcode() {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode((dpiCod), BarcodeFormat.CODE_128, 450, 150);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            codigoBarras.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    /**
     * verify all the permissions passed to the array
     * @param permissions
     */
    public void verifyPermissions(String[] permissions) {
        Log.d(TAG, "verifyPermissions: verifing permissions");
        ActivityCompat.requestPermissions(getActivity(),permissions,VERIFY_PERMISSIONS_REQUEST);
    }

    /**
     * check an array of permissions
     * @param permissions
     * @return
     */
    public boolean checkPermissionArray(String[] permissions) {
        Log.d(TAG, "checkPermissionArray: checking  permissions array.");

        for (int i = 0; i< permissions.length; i++){
            String check = permissions[i];
            if (!checkPermissions(check)){
                return false;
            }
        }
        return true;
    }

    /**
     * check a single permission is it has been verified
     * @param permission
     * @return
     */
    public boolean checkPermissions(String permission) {
        Log.d(TAG, "checkPermissions: checking permission: " + permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(getActivity(), permission);

        if (permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermissions: \n Permission was not granted for: " + permission);
            return false;
        }else{
            Log.d(TAG, "checkPermissions: \n Permission was granted for: " + permission);
            return true;
        }
    }

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
