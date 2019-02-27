package com.example.asus.busguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class Bienvenido extends AppCompatActivity {

    Spinner combolugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        combolugares=findViewById(R.id.spinnerlugares);

        ArrayAdapter<CharSequence> adapter
    }
}
