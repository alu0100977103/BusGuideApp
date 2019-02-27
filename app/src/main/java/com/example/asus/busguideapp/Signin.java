package com.example.asus.busguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.regex.Pattern;

public class Signin extends AppCompatActivity {

    private static final Object TAG = "Signin" ;
    private EditText mail, Contraseña, Repetir;
    Integer aux=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insign);

        mail = (EditText) findViewById(R.id.mail);
        Contraseña = (EditText) findViewById(R.id.Contraseña);
        Repetir = (EditText) findViewById(R.id.Repetir);
    }

    public void registrar(View view){
        String email =mail.getText().toString();
        String pass=Contraseña.getText().toString();
        String repeaters=Repetir.getText().toString();
        
        if ((TextUtils.isEmpty(email)) || (TextUtils.isEmpty(pass))){
            Toast.makeText(Signin.this, "No debe existir ningún campo vacío", Toast.LENGTH_LONG).show();
            return;
        }else{
            if(pass.length()<6){
                Toast.makeText(Signin.this, "La contraseña debe ser de tamaño superior o igual a 6", Toast.LENGTH_LONG).show();
                return;
            }else {
                if(Objects.equals(pass,repeaters)){
                    Pattern pattern = Patterns.EMAIL_ADDRESS;
                    if(pattern.matcher(email).matches()==false){
                        Toast.makeText(Signin.this, "El e-mail no esta en formato letra@gmail.com", Toast.LENGTH_LONG).show();
                        return;
                    }
                }else{
                    Toast.makeText(Signin.this, "Las contraseñas deben coincidir", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        registrarUsuario(email,pass);
    }

    private void registrarUsuario(String email,String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Signin.this, "Este e-mail ya esta registrado", Toast.LENGTH_LONG).show(); //Tiene que ser mismo correo y pass para uqe pase esot revisar
                }else{
                    aux=1;
                }
            }
        });
        if(aux==1) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass);
            Toast.makeText(Signin.this, "Usuario registrado", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Signin.this,Bienvenido.class));
        }
    }
}
