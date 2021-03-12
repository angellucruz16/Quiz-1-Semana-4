package com.example.quiz1_s5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private Button botonRegistrar;
private TextView encuestados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonRegistrar = findViewById(R.id.botonRegistrar);
        encuestados = findViewById(R.id.encuestados);
        mostrarLista();
        //String encuestadoTxt = getSharedPreferences("");

        //SetOnClicks

        botonRegistrar.setOnClickListener(
                v -> {
                    Intent i = new Intent(this, NuevoRegistro.class);
                    startActivity(i);
                }
        );
    }
    protected void onResume () {
        super.onResume();
        mostrarLista();
    }
    public void mostrarLista () {
        String encuestadosUs = getSharedPreferences("encuestadosResult", MODE_PRIVATE).getString("datosUsuarios","");
        encuestados.setText(encuestadosUs);
        Log.e("::", encuestadosUs);
    }
}