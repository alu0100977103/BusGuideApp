package com.example.asus.busguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class Bienvenido extends AppCompatActivity implements View.OnClickListener {

    Spinner combolugares;
    String aux;
    TextView paradas,numeroparadas, lugar,detectar,tiempotitulo,tiempo,eventos;
    Button buscar,cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        buscar= findViewById(R.id.buscar);
        combolugares= findViewById(R.id.spinnerlugares);
        paradas = findViewById(R.id.paradas);
        numeroparadas = findViewById(R.id.numeroparadas);
        lugar =findViewById(R.id.lugar);
        cancelar =findViewById(R.id.cancelar);
        detectar = findViewById(R.id.detectar);
        tiempotitulo=findViewById(R.id.tiempotitulo);
        tiempo=findViewById(R.id.tiempo);
        eventos = findViewById(R.id.eventos);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.combo_lugare,android.R.layout.simple_spinner_item);
        combolugares.setAdapter(adapter);

        combolugares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aux=parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        buscar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.buscar:
                if(Objects.equals(aux,"Intercambiador La Laguna")){
                    numeroparadas.setText("2");
                    lugar.setText("Intercambiador La Laguna");
                    tiempo.setText("10 minutos");
                }else{
                    numeroparadas.setText("3");
                    lugar.setText("Intercambiador Santa Cruz");
                    tiempo.setText("15 minutos");
                }

                paradas.setVisibility(View.VISIBLE);
                numeroparadas.setVisibility(View.VISIBLE);
                buscar.setVisibility(View.GONE);
                combolugares.setVisibility(View.GONE);
                lugar.setVisibility(View.VISIBLE);
                detectar.setVisibility(View.VISIBLE);
                tiempotitulo.setVisibility(View.VISIBLE);
                tiempo.setVisibility(View.VISIBLE);
                cancelar.setVisibility(View.VISIBLE);
                eventos.setVisibility(View.VISIBLE);

                break;

            case R.id.cancelar:

                buscar.setVisibility(View.VISIBLE);
                paradas.setVisibility(View.GONE);
                numeroparadas.setVisibility(View.GONE);
                combolugares.setVisibility(View.VISIBLE);
                lugar.setVisibility(View.GONE);
                detectar.setVisibility(View.GONE);
                tiempotitulo.setVisibility(View.GONE);
                tiempo.setVisibility(View.GONE);
                eventos.setVisibility(View.GONE);
                cancelar.setVisibility(View.GONE);

                break;
        }
    }
}
