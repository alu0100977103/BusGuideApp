package com.example.asus.busguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Bienvenido extends AppCompatActivity implements View.OnClickListener {

    Spinner combolugares;
    String aux;
    TextView paradas,numeroparadas;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        buscar= (Button) findViewById(R.id.buscar);
        combolugares= (Spinner) findViewById(R.id.spinnerlugares);
        paradas = (TextView) findViewById(R.id.paradas);
        numeroparadas = (TextView) findViewById(R.id.numeroparadas);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.combo_lugare,android.R.layout.simple_spinner_item);
        combolugares.setAdapter(adapter);

        combolugares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aux=parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),"Seleccionado"+parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG);
            }

            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        buscar.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.buscar:
                if(aux=="Intercambiador La Laguna"){
                    numeroparadas.setText("2");
                }
                paradas.setVisibility(View.VISIBLE);
                numeroparadas.setVisibility(View.VISIBLE);
                buscar.setVisibility(View.GONE);
                combolugares.setVisibility(View.GONE);
                break;
        }
    }
}
