package com.example.quiz1_s5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;

public class Sintomas extends AppCompatActivity {
    private CheckBox sintomasPregUno;
    private CheckBox sintomasPregDos;
    private CheckBox sintomasPregTres;
    private CheckBox sintomasPregCuatro;
    private CheckBox sintomasPregCinco;
    private CheckBox sintomasPregSeis;
    private CheckBox sintomasSiete;
    private Button finalizarBoton;
    private int puntajeSin, puntajeNexo;
    private int puntajeFinal;
    private String nombreSin, idSin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas);
        sintomasPregUno = findViewById(R.id.sintomasPregUno);
        sintomasPregDos= findViewById(R.id.sintomasPregDos);
        sintomasPregTres= findViewById(R.id.sintomasPregTres);
        sintomasPregCuatro= findViewById(R.id.sintomasPregCuatro);
        sintomasPregCinco= findViewById(R.id.sintomasPregCinco);
        sintomasPregSeis= findViewById(R.id.sintomasPregSeis);
        sintomasSiete= findViewById(R.id.sintomasSiete);
        finalizarBoton= findViewById(R.id.finalizarBoton);

        nombreSin = getIntent().getExtras().getString("nombreNexo");
        Log.e(":::::", nombreSin);
        idSin = getIntent().getExtras().getString("idNexo");
        puntajeNexo = getIntent().getExtras().getInt("puntajeNexo");

        finalizarBoton.setEnabled(false);
        continuarBoton();

        //SetOnClicks
        finalizarBoton.setOnClickListener(
                v -> {
                    puntosCheckBox();
                    puntajeFinal = puntajeNexo + puntajeSin;
                    guardarDatos();
                    finish();
                }
        );
    }
    public void puntosCheckBox() {

        if (sintomasPregUno.isChecked()) {
            puntajeSin += 4;
        }
        if (sintomasPregDos.isChecked()) {
            puntajeSin += 4;
        }
        if (sintomasPregTres.isChecked()) {
            puntajeSin += 4;
        }
        if (sintomasPregCuatro.isChecked()) {
            puntajeSin += 4;
        }
        if (sintomasPregCinco.isChecked()) {
            puntajeSin += 4;
        }
        if (sintomasPregSeis.isChecked()) {
            puntajeSin += 4;
        }
        if (sintomasSiete.isChecked()) {
            puntajeSin += 0;
        }

    }

    private void guardarDatos () {
        SharedPreferences preferences = getSharedPreferences("encuestadosResult",MODE_PRIVATE);
        String puntos = String.valueOf(puntajeFinal); //Casteamos
        String usuarioDatos = nombreSin + "," + idSin + "," + puntos + "\n";
        String usuarioDatosActual = preferences.getString("datosUsuarios", "");
        preferences.edit().putString("datosUsuarios", usuarioDatosActual + usuarioDatos).apply();
    }
    public void continuarBoton () {
        new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(320);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (sintomasPregUno.isChecked() || sintomasPregDos.isChecked() || sintomasPregTres.isChecked() || sintomasPregCuatro.isChecked() || sintomasPregCinco.isChecked()|| sintomasPregSeis.isChecked() || sintomasSiete.isChecked()) {
                            runOnUiThread(
                                    () -> {
                                        finalizarBoton.setEnabled(true);
                                    }
                            );
                        } else {
                            runOnUiThread(
                                    () -> {
                                        finalizarBoton.setEnabled(false);
                                    }
                            );
                        }
                    }
                }
        ).start();
    }
}