package com.example.asus.busguideapp;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT= 1;
    private Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothAdapter mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage(R.string.fundir);
            builder.setNegativeButton(R.string.aceptar,null);
            Dialog dialog=builder.create();
            dialog.show();
        }else {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
        regist= findViewById(R.id.regist);
        regist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent Register = new Intent(MainActivity.this, Signin.class);
                startActivity(Register);
            }
        });
    }

    public void Registrar(View view){
        Intent miIntent=new Intent(MainActivity.this,Signin.class);
    }
}
