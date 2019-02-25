package com.example.asus.busguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends AppCompatActivity {

    private EditText mail, Contraseña, Repetir;
    private Button signin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insign);

        mAuth = FirebaseAuth.getInstance();

        signin=(Button) findViewById(R.id.signin);
        mail =(EditText) findViewById(R.id.mail);
        Contraseña = (EditText) findViewById(R.id.Contraseña);
        Repetir = (EditText) findViewById(R.id.Repetir);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email =mail.getText().toString().trim();
                String password=Contraseña.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signin.this,"Introducir correo",Toast.LENGTH_LONG).show();
                }
                Intent Register = new Intent(Signin.this, Conexion.class);
                startActivity(Register);

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }

            // ...
        }
    });
}
