package com.example.asus.busguideapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Setting extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 101;
    EditText contraseña,Nombre;
    Button cambiar,salir;
    ImageView camarafotos;

    Uri uriprofile;
    FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion);

        contraseña = findViewById(R.id.contraseña);
        Nombre = findViewById(R.id.Nombre);
        cambiar= findViewById(R.id.cambiar);
        salir = findViewById(R.id.salir);
        camarafotos=findViewById(R.id.camarafotos);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user= mAuth.getCurrentUser();

        camarafotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this,Bienvenido.class));
            }
        });

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=Nombre.getText().toString();
                String Contraseña = contraseña.getText().toString();
                Usuario user = new Usuario(nombre,Integer.valueOf(Contraseña));
                mDatabase.child("usuario").child(nombre).setValue(user); //Donde nombre ponemos correo
            }
        });

    }

    /*private void saveUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();
        String displayName = user.getText().toString();

        if(user!=null){
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(user).build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Setting.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            uriprofile=data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriprofile);
                camarafotos.setImageBitmap(bitmap);
                uploadImageToFirebaseStorage();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void uploadImageToFirebaseStorage() {
        final StorageReference profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");
        if(uriprofile!=null){
            profileImageRef.putFile(uriprofile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Setting.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showImageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }
}
