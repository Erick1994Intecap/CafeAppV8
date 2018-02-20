package com.aesc.santos.gitanoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aesc.santos.gitanoapp.Entidades.VolleySingleton;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {

    StringRequest stringRequest;
    EditText dpi, nombre, apellido, fecha, correo, telefono, pass;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        dpi = findViewById(R.id.txtDpi);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        fecha = findViewById(R.id.txtFechaNac);
        correo = findViewById(R.id.txtCorreo);
        telefono = findViewById(R.id.txtTelefono);
        pass = findViewById(R.id.txtContaseña);

        guardar = findViewById(R.id.btnGuardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });


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
                    Toast.makeText(getApplicationContext(), "Sea Registrado con exito!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            //Y ESTA PARTE SE EJECUTA CUANDO DA ALGUN ERROR
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No se ah podido conectar", Toast.LENGTH_SHORT).show();

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
        VolleySingleton.getIntanciaVolley(this).addToRequestQueue(stringRequest);
    }
}
