package com.example.asus.busguideapp;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.busguideapp.Utilidades.Utilidades;
import com.example.asus.busguideapp.entidades.Usuario;

public class Signin extends AppCompatActivity {

    private static Integer id = 0;
    private EditText Nombre, mail, Contraseña, Repetir;
    private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insign);
        id= id+1;
        signin=(Button) findViewById(R.id.signin);
        Nombre = (EditText) findViewById(R.id.Nombre);
        mail =(EditText) findViewById(R.id.mail);
        Contraseña = (EditText) findViewById(R.id.Contraseña);
        Repetir = (EditText) findViewById(R.id.Repetir);
        Conexionsqlitehelper conn=new Conexionsqlitehelper(this,"bd_usuarios",null,1); //Rvisar creo que esta mal
        signin=(Button) findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent Register = new Intent(Signin.this, Conexion.class);
                startActivity(Register);
            }
        });
    }

    public void Registro (View view){
        registrarUsuario();
    }

    private void registrarUsuario() {
        Conexionsqlitehelper conn=new Conexionsqlitehelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db= conn.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE, Nombre.getText().toString());
        values.put(Utilidades.CAMPO_PASSWORD, Contraseña.getText().toString());
        values.put(Utilidades.CAMPO_EMAIL, mail.getText().toString());

        Long idResultado=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);
        db.close();
    }
}
