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
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import static java.sql.Types.NULL;

public class Bienvenido extends AppCompatActivity implements View.OnClickListener {

    private static final Object Bienvenido = 1 ;
    private static final Object TAG = Bienvenido;
    private static final int REQUEST_DISCOVER_BT = 1;
    Spinner combolugares;
    String aux;
    TextView paradas,numeroparadas, lugar,detectar,tiempotitulo,tiempo,eventos;
    Button buscar,cancelar;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayList<String> mDeviceList = new ArrayList<>();

    private final BroadcastReceiver mReceiver= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(String.valueOf(TAG),"mas tenso");
            for(int i=0;i<mDeviceList.size();i++){
                mDeviceList.set(i, null);
            }
            String action=intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){  //Acciones cuando encuentras un nuevo dispositivos
                BluetoothDevice device =intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDeviceList.add(device.getAddress());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        buscar= findViewById(R.id.buscar);
        combolugares= findViewById(R.id.spinnerlugares);
        paradas = findViewById(R.id.paradas);
        numeroparadas = findViewById(R.id.numeroparadas);
        lugar =findViewById(R.id.lugar);
        cancelar =findViewById(R.id.cancelar);
        detectar = findViewById(R.id.detectar);
        tiempotitulo=findViewById(R.id.tiempotitulo);
        tiempo=findViewById(R.id.tiempo);
        eventos = findViewById(R.id.eventos);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.combo_lugare,android.R.layout.simple_spinner_item);
        combolugares.setAdapter(adapter);

        combolugares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aux=parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        buscar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.buscar:
                while(mDeviceList.size()>0){
                }
                /* if (mBluetoothAdapter.isDiscovering()) {
                    Log.e(String.valueOf(TAG),"Tensooooo");
                    mBluetoothAdapter.cancelDiscovery();
                }

                mBluetoothAdapter.startDiscovery();
                /*Intent intent=new Intent(mBluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(intent,REQUEST_DISCOVER_BT);

                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(mReceiver,filter);*/
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                for(int i=0;i<mDeviceList.size();i++) {
                    Log.e(String.valueOf(TAG),"Tenso");
                    if (mDeviceList.get(i).equals("FC:23:60:ED:0B:B7")) {
                        startActivity(new Intent(Bienvenido.this,Beacon.class));
                    }
                }

                if(Objects.equals(aux,"Intercambiador La Laguna")){
                    numeroparadas.setText("2");
                    lugar.setText("Intercambiador La Laguna");
                    tiempo.setText("10 minutos");
                }else{
                    numeroparadas.setText("3");
                    lugar.setText("Intercambiador Santa Cruz");
                    tiempo.setText("15 minutos");
                }

                paradas.setVisibility(View.VISIBLE);
                numeroparadas.setVisibility(View.VISIBLE);
                buscar.setVisibility(View.GONE);
                combolugares.setVisibility(View.GONE);
                lugar.setVisibility(View.VISIBLE);
                detectar.setVisibility(View.VISIBLE);
                tiempotitulo.setVisibility(View.VISIBLE);
                tiempo.setVisibility(View.VISIBLE);
                cancelar.setVisibility(View.VISIBLE);
                eventos.setVisibility(View.VISIBLE);

                break;

            case R.id.cancelar:

                buscar.setVisibility(View.VISIBLE);
                paradas.setVisibility(View.GONE);
                numeroparadas.setVisibility(View.GONE);
                combolugares.setVisibility(View.VISIBLE);
                lugar.setVisibility(View.GONE);
                detectar.setVisibility(View.GONE);
                tiempotitulo.setVisibility(View.GONE);
                tiempo.setVisibility(View.GONE);
                eventos.setVisibility(View.GONE);
                cancelar.setVisibility(View.GONE);

                break;
        }
    }
}
