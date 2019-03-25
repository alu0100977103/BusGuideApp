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
import android.widget.ImageView;
import android.widget.ListView;
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
    ImageView imagen;
    private String mDeviceList=null;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mBluetoothdevice;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        BluetoothDevice device;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.i(String.valueOf(getBaseContext()), "Bluetooth");
            if (mBluetoothdevice.ACTION_FOUND.equals(action)) {
                device = intent.getParcelableExtra(mBluetoothdevice.EXTRA_DEVICE);
                if(device.getName().equals("iBKS USB")) {
                    mDeviceList=device.getAddress();
                }
                Log.i(String.valueOf(getBaseContext()), "Blue" + device);
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
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
        imagen = findViewById(R.id.imagen);

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

        mBluetoothAdapter.startDiscovery();
        registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        buscar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        imagen.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.buscar:
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

                //mDeviceList="FC:23:60:ED:0B:B7" ;

                //if (mDeviceList.equals("FC:23:60:ED:0B:B7")) {
                    //Log.i(String.valueOf(getBaseContext()), "Bluepami");
                 //   startActivity(new Intent(Bienvenido.this,Beacon.class));
                //}
                Log.i(String.valueOf(getBaseContext()), "Bluecito" + mDeviceList);
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

            case R.id.imagen:
                startActivity(new Intent(Bienvenido.this,Setting.class));
                break;
        }
    }
}
