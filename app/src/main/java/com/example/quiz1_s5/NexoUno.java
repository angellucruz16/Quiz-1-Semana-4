package com.example.quiz1_s5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

public class NexoUno extends AppCompatActivity {
    private CheckBox nexoPregUno;
    private CheckBox nexoPregDos;
    private CheckBox nexoPregTres;
    private CheckBox nexoPregCuatro;
    private CheckBox nexoPregCinco;
    private Button btnContinuar;
    private int puntaje;
    private String nombre;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nexo_uno);
        nexoPregUno = findViewById(R.id.nexoPregUno);
        nexoPregDos = findViewById(R.id.nexoPregDos);
        nexoPregTres = findViewById(R.id.nexoPregTres);
        nexoPregCuatro = findViewById(R.id.nexoPregCuatro);
        nexoPregCinco = findViewById(R.id.nexoPregCinco);
        btnContinuar = findViewById(R.id.btnContinuar);
        puntaje = 0;
        nombre = getIntent().getExtras().getString("nombre");
        id = getIntent().getExtras().getString("id");

        continuarBoton();
        btnContinuar.setEnabled(false);

        btnContinuar.setOnClickListener(
                (v) -> {
                        puntosCheckBox();
                        Intent i = new Intent(this, Sintomas.class);
                        i.putExtra("nombreNexo", nombre);
                        i.putExtra("idNexo", id);
                        i.putExtra("puntajeNexo", puntaje);
                        startActivity(i);
                        finish();
                }
        );
    }

    public void puntosCheckBox() {

        if (nexoPregUno.isChecked()) {
            puntaje += 3;
        }
        if (nexoPregDos.isChecked()) {
            puntaje += 3;
        }
        if (nexoPregTres.isChecked()) {
            puntaje += 3;
        }
        if (nexoPregCuatro.isChecked()) {
            puntaje += 3;
        }
        if (nexoPregCinco.isChecked()) {
            puntaje += 0;
        }
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
                        if (nexoPregUno.isChecked() || nexoPregDos.isChecked() || nexoPregTres.isChecked() || nexoPregCuatro.isChecked() || nexoPregCinco.isChecked()) {
                            runOnUiThread(
                                    () -> {
                                        btnContinuar.setEnabled(true);
                                    }
                            );
                        } else {
                            runOnUiThread(
                                    () -> {
                                        btnContinuar.setEnabled(false);
                                    }
                            );
                        }
                    }
                }
        ).start();
    }
}
