package com.example.asus.busguideapp;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import static android.widget.Toast.*;


public class Conexion extends AppCompatActivity{
    private static final int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView direction = (TextView) findViewById(R.id.Direccion);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        final ArrayList<String> mArrayAdapter =null;
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
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            mBluetoothAdapter.startDiscovery();

            final BroadcastReceiver mReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                        // Se ha encontrado un dispositivo Bluetooth
                        // Se obtiene la información del dispositivo del intent
                        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        Log.i("Conexión", "Dispositivo encontrado: " + device.getName() + "; MAC " + device.getAddress());
                    }
                }
            };

            // Se registra el broadcast receiver
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mReceiver, filter);
            
        }
    }

}
