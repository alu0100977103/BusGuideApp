package com.example.asus.busguideapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regist=(Button) findViewById(R.id.regist);
        regist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent Register = new Intent(MainActivity.this, Signin.class);
                startActivity(Register);
            }
        });
    }

    public void Registrar(View view){
        Intent miIntent =null;
        miIntent=new Intent(MainActivity.this,Signin.class);
    }
}
