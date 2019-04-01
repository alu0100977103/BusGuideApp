package com.example.asus.busguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    private EditText mail, Contraseña;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upsign);

        mail = (EditText) findViewById(R.id.mail);
        Contraseña = (EditText) findViewById(R.id.Contraseña);

        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser(); //user.getEmail() es el correo con el que se conecta
            }
        };
    }

    public void iniciar(View view){
        String email =mail.getText().toString();
        String pass=Contraseña.getText().toString().trim();

        if ((TextUtils.isEmpty(email)) || (TextUtils.isEmpty(pass))){
            Toast.makeText(Signup.this, "No debe existir ningún campo vacío", Toast.LENGTH_LONG).show();
            return;
        }else{
            iniciarusuario(email,pass);
        }

    }

    private void iniciarusuario(String email,String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Signup.this, "Usuario entrando", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Signup.this,Inicio.class));
                }else{
                    Toast.makeText(Signup.this, "Usuario o contraseña incorrecto", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    protected  void onStop(){
        super.onStop();
        if(mAuthListener!=null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}

