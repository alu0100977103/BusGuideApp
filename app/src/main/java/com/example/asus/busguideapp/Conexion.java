package com.example.asus.busguideapp;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.drm.DrmStore;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class Conexion extends AppCompatActivity{
    class Dispo{
        String nombre;
        String direccion;

        Dispo(String nombre, String direccion){
            this.nombre=nombre;
            this.direccion=direccion;
        }

    }

    private List<Dispo> dispositives;
    private void initializeData(){
        dispositives=new ArrayList<>();
        dispositives.add(new Dispo("Dispositivo 1","33333"));
    }
    private static final String TAG="Conexion";
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        RecyclerView recyclerView=findViewById(R.id.dispositivos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RVAdapter adapter=new RVAdapter(dispositives);

        recyclerView.setAdapter(adapter);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);

        registerReceiver(mReceiver, filter);
    }

    public void InicioBusqueda(View view){
        if(mBluetoothAdapter.isDiscovering()){
            Log.d(TAG,"InicioBusqueda: Ya se esta buscando dispositivo");
        }else if(mBluetoothAdapter.startDiscovery()){
            Log.d(TAG,"InicioBusqueda: Buscando dispositivos");
        }else{
            Log.d(TAG,"InicioBusqueda: Error al buscar el dispositivo");
        }
    }

    protected void onDestroy(){
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            assert action != null;
            switch (action){
                case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                    Log.d(TAG,"onReceive: Busqueda de dispositivos iniciada");
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    Log.d(TAG,"onReceive: Busqueda de dispositivos finalizada");
                    break;
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    dispositives.add(new Dispo(device.getName(),device.getAddress()));
                    break;
            }
        }
    };

}
