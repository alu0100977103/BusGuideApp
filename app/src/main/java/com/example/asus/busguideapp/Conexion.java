package com.example.asus.busguideapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;


public class Conexion extends AppCompatActivity{
    Button buscar;
    BluetoothAdapter mBluetoothAdapter;
    ListView listview;
    private ArrayList<String> mDeviceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        buscar=findViewById(R.id.buscar);
        listview = findViewById(R.id.listview);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void InicioBusqueda(View view){
        Set<BluetoothDevice> devices =mBluetoothAdapter.getBondedDevices();
        for(BluetoothDevice device: devices){
            mDeviceList.add(device.getAddress());
            listview.setAdapter(new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,mDeviceList ));
        }
    }

}