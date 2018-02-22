package com.aesc.santos.gitanoapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private static final String TAG = "LoginActivity";


    EditText txtUsuario, txtPassword;
    Button btnIngresar;

    RequestQueue mRequestQueue;
    JsonObjectRequest mJsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtUsuario = (EditText)findViewById(R.id.etUsername);
        txtPassword = (EditText)findViewById(R.id.etPassword);
        btnIngresar = (Button)findViewById(R.id.btnLogin);

        mRequestQueue = Volley.newRequestQueue(this);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCesion();
            }
        });

    }

    private void initCesion() {
        String url = "http://adrax.hol.es/php/consultarusuario.php?DPI=" + txtUsuario.getText().toString() + "&password=" + txtPassword.getText().toString();

        mJsonObject = new JsonObjectRequest(Request.Method.GET, url, null,this,this);
        mRequestQueue.add(mJsonObject);
     }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error al iniciar secion", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Intent intent = new Intent(getApplicationContext(), BodyActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
