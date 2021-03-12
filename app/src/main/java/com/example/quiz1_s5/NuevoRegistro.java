package com.example.quiz1_s5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.StringSearch;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NuevoRegistro extends AppCompatActivity {
    private EditText nombre;
    private EditText identificacion;
    private Button botonContinuar;
    private boolean idCorrecta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_registro);
        nombre = findViewById(R.id.nombre);
        identificacion = findViewById(R.id.identificacion);
        botonContinuar = findViewById(R.id.botonContinuar);

        //setOnClicks
        botonContinuar.setOnClickListener(
                v -> {
                    verificarId();
                    String getNombre = nombre.getText().toString();
                    String getId = identificacion.getText().toString();
                    if (getNombre == null || getNombre.isEmpty() || getId == null || getId.isEmpty() ) {
                        Toast.makeText(this, "Tiene campos sin completar", Toast.LENGTH_SHORT).show();
                        idCorrecta = false;
                    } else if (idCorrecta){
                        Intent i = new Intent(this, NexoUno.class);
                        i.putExtra("nombre", getNombre);
                        i.putExtra("id", getId);
                        startActivity(i);
                        finish();
                    }
                }
        );
    }
public void verificarId (){
        idCorrecta = true;
    String Usuario = getSharedPreferences("encuestadosResult",MODE_PRIVATE).getString("datosUsuarios" , "Sin datos");

    if (Usuario.contains(identificacion.getText().toString())){
        Toast.makeText(this,"Este id ya está registrado", Toast.LENGTH_SHORT).show();
        idCorrecta = false;
    }
}
}