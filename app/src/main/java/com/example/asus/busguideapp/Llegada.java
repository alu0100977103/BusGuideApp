package com.example.asus.busguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Llegada extends AppCompatActivity implements View.OnClickListener {

    Button Okey;
    Bundle lugar;
    EditText Mision;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrive);

        Okey=findViewById(R.id.Okey);
        Mision=findViewById(R.id.Mision);
        Okey.setOnClickListener(this);

        lugar = getIntent().getExtras();
        String mision_obt= lugar.getString("Cumplida");

        Mision.setText("Usted ya ha llegado ha su destino " + mision_obt + " Esperemos que tenga un buen día.");
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Llegada.this,Inicio.class));
    }
}
