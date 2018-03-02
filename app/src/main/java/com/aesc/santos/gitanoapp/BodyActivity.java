package com.aesc.santos.gitanoapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aesc.santos.gitanoapp.Entidades.AndroidVersion;
import com.aesc.santos.gitanoapp.Entidades.Tiendaszona;
import com.aesc.santos.gitanoapp.Entidades.Usuario;
import com.aesc.santos.gitanoapp.Fragments.Acerca_de;
import com.aesc.santos.gitanoapp.Fragments.CreditosFragment;
import com.aesc.santos.gitanoapp.Fragments.NotificacionesFragment;
import com.aesc.santos.gitanoapp.Fragments.ProductoDetalleFragment;
import com.aesc.santos.gitanoapp.Fragments.ProductosFragment;
import com.aesc.santos.gitanoapp.Fragments.PromocionesFragment;
import com.aesc.santos.gitanoapp.Fragments.PuntosFragment;
import com.aesc.santos.gitanoapp.Fragments.TiendaDetalleFragment;
import com.aesc.santos.gitanoapp.Fragments.TiendaFragment;
import com.aesc.santos.gitanoapp.Intefaces.IComunicaFragments;

import java.util.ArrayList;

public class BodyActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        NotificacionesFragment.OnFragmentInteractionListener,
        ProductosFragment.OnFragmentInteractionListener,
        PromocionesFragment.OnFragmentInteractionListener,
        PuntosFragment.OnFragmentInteractionListener,
        TiendaFragment.OnFragmentInteractionListener,
        IComunicaFragments, Acerca_de.OnFragmentInteractionListener, CreditosFragment.OnFragmentInteractionListener {


    private static final String TAG = "BodyActivity";

    TiendaDetalleFragment mTiendaFragmentDetalle;
    ProductoDetalleFragment mProductoDetalleFragment;
    PuntosFragment mPuntosFragment;

    private final int REQUEST_ACCESS_FINE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        SharedPreferences mPreferencesDPI = getSharedPreferences("DatoDPI", this.MODE_PRIVATE);
        long valDPI = mPreferencesDPI.getLong("DPI", 35968745);

        SharedPreferences sharedPred = getSharedPreferences("ArchivoSP", this.MODE_PRIVATE);
        String nombreUser = sharedPred.getString("MiDato", "Error");

        //canal.setText(nombreUser);

        SharedPreferences mPreferences = getSharedPreferences("DatoA", this.MODE_PRIVATE);
        String apellidoSP = mPreferences.getString("MiDato2", "Error");
        //Toast.makeText(this, String.valueOf(valDPI), Toast.LENGTH_SHORT).show();

        if (valDPI == 35968745 || valDPI == 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},REQUEST_ACCESS_FINE);
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE},REQUEST_ACCESS_FINE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //ESTO PERMITE CAMBIARLE EL TEXTO EN EL ENCABEZADO
        View hView = navigationView.getHeaderView(0);
        TextView nav_user = (TextView) hView.findViewById(R.id.nombre_del_tema);
        nav_user.setText(nombreUser.toString() + " " + apellidoSP);
        //AQUI TERMINA LO DEL ENCABEZDO

        navigationView.setNavigationItemSelectedListener(this);

        mPuntosFragment = new PuntosFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mPuntosFragment).commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment = null;
        boolean fragmentSeleccionado = false;

        if (id == R.id.productos) {
            miFragment = new ProductosFragment();
            fragmentSeleccionado = true;
            Log.d(TAG, "onNavigationItemSelected: ProductosFragment");
        } else if (id == R.id.tienda) {
            miFragment = new TiendaFragment();
            fragmentSeleccionado = true;
            Log.d(TAG, "onNavigationItemSelected: TiendaFragment");
        } else if (id == R.id.punto) {
            miFragment = new PuntosFragment();
            fragmentSeleccionado = true;
            Log.d(TAG, "onNavigationItemSelected: PuntoFragment");
        } else if (id == R.id.promociones) {
            miFragment = new PromocionesFragment();
            fragmentSeleccionado = true;
            Log.d(TAG, "onNavigationItemSelected: PromocinesFragment");
        } else if (id == R.id.favoritos) {
            miFragment = new NotificacionesFragment();
            fragmentSeleccionado = true;
            Log.d(TAG, "onNavigationItemSelected: NotificacionesFragment");
        } else if (id == R.id.logOut) {

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            SharedPreferences settings = this.getSharedPreferences("DatoDPI", Context.MODE_PRIVATE);
            settings.edit().remove("DPI").commit();
            startActivity(intent);

        } /*else if (id == R.id.siguenos) {
            Toast.makeText(this, "Siguenos", Toast.LENGTH_SHORT).show();
        }*/ else if (id == R.id.abtUs) {
            miFragment = new CreditosFragment();
            fragmentSeleccionado = true;
            Log.d(TAG, "AbtUSFragment");

        } else if (id == R.id.acerca_de) {
            miFragment = new Acerca_de();
            fragmentSeleccionado = true;

        }

        if (fragmentSeleccionado == true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, miFragment).addToBackStack(null).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void datosUsuario(Usuario usuario) {

        //getSupportFragmentManager().beginTransaction().replace(R.id.container, mPuntosFragment).addToBackStack(null).commit();
    }

    @Override
    public void enviarProducto(int position) {
        mProductoDetalleFragment = new ProductoDetalleFragment();

        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putInt("key", position);
        mProductoDetalleFragment.setArguments(bundleEnvio);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mProductoDetalleFragment).addToBackStack(null).commit();
    }

    @Override
    public void enviarZonas(int position) {
        //48464654
        mTiendaFragmentDetalle = new TiendaDetalleFragment();
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putInt("key", position);
        mTiendaFragmentDetalle.setArguments(bundleEnvio);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mTiendaFragmentDetalle).addToBackStack(null).commit();
    }
}
