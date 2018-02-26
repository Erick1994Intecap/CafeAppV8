package com.aesc.santos.gitanoapp;


import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.aesc.santos.gitanoapp.Entidades.Usuario;
import com.aesc.santos.gitanoapp.Fragments.LoginFragment;
import com.aesc.santos.gitanoapp.Fragments.PuntosFragment;
import com.aesc.santos.gitanoapp.Intefaces.IComunicaFragments;


public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {
//wefsdafsdfsdf
    private static final String TAG = "LoginActivity";

    PuntosFragment mPuntosFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Fragment miFragment= new LoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,miFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
