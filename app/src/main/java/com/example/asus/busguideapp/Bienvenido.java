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
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class Bienvenido extends AppCompatActivity implements View.OnClickListener {

    private static final Object Bienvenido = 1 ;
    private static final Object TAG = Bienvenido;
    TextView paradas,numeroparadas, lugar,detectar,tiempotitulo,tiempo;
    Button buscar,cancelar;
    CheckedTextView uno,dos,tres,cuatro;
    private String mDeviceList=null;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mBluetoothdevice;
    Bundle datos, salida;
    String datos_obt, salida_obt;
    String aux=null;


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        BluetoothDevice device;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.i(String.valueOf(getBaseContext()), "Bluetooth");
            if (mBluetoothdevice.ACTION_FOUND.equals(action)) {
                device = intent.getParcelableExtra(mBluetoothdevice.EXTRA_DEVICE);
                mDeviceList=device.getAddress();
                Log.i(String.valueOf(getBaseContext()), "Blue" + device);
                if (mDeviceList.equals("FC:23:60:ED:0B:B7")) {
                    Intent cambiar = new Intent(Bienvenido.this,Beacon.class);
                    mBluetoothAdapter.cancelDiscovery();
                    cambiar.putExtra("Datos",mDeviceList);
                    cambiar.putExtra("Destino",datos_obt);
                    cambiar.putExtra("Salida",salida_obt);
                    startActivity(cambiar);
                }
                if(mDeviceList.equals("E2:C3:B1:E0:2D:8B")){
                    Intent cambiar = new Intent(Bienvenido.this,Beacon.class);
                    mBluetoothAdapter.cancelDiscovery();
                    cambiar.putExtra("Datos",mDeviceList);
                    cambiar.putExtra("Destino",datos_obt);
                    cambiar.putExtra("Salida",salida_obt);
                    startActivity(cambiar);
                }
                if(mDeviceList.equals("E1:FF:56:62:7F:F3")){
                    Intent cambiar = new Intent(Bienvenido.this,Beacon.class);
                    mBluetoothAdapter.cancelDiscovery();
                    cambiar.putExtra("Datos",mDeviceList);
                    cambiar.putExtra("Destino",datos_obt);
                    cambiar.putExtra("Salida",salida_obt);
                    startActivity(cambiar);
                }
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

        datos = getIntent().getExtras();
        salida=getIntent().getExtras();
        datos_obt= datos.getString("Datos");
        salida_obt=salida.getString("Salida");

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        buscar= findViewById(R.id.buscar);

        paradas = findViewById(R.id.paradas);
        numeroparadas = findViewById(R.id.numeroparadas);
        lugar =findViewById(R.id.lugar);
        cancelar =findViewById(R.id.cancelar);
        detectar = findViewById(R.id.detectar);
        tiempotitulo=findViewById(R.id.tiempotitulo);
        tiempo=findViewById(R.id.tiempo);
        uno=findViewById(R.id.Uno);
        tres=findViewById(R.id.Tres);
        dos=findViewById(R.id.Dos);
        cuatro=findViewById(R.id.Cuatro);
        cancelar.setOnClickListener(this);

        lugar.setText(salida_obt + "-" + datos_obt);
        if(mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.cancelDiscovery();
        }
        mBluetoothAdapter.startDiscovery();
        registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        if((datos_obt.equals("Dulceria el Rayo") && salida_obt.equals("Calle La Laguna Nº1")) ||
                (salida_obt.equals("Intercambiador La Laguna") && datos_obt.equals("Dulceria el Rayo"))){
            if(salida_obt.equals("Calle La Laguna Nº1")){
                numeroparadas.setText("2");
                tiempo.setText("20 minutos");
                uno.setText("No se baje en Calle Las Peras, continue hasta la siguiente parada");
                uno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uno.setChecked(true);
                    }
                });
                dos.setText("Bajese en la parada Destino: " + datos_obt);
                dos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uno.setChecked(true);
                    }
                });
                tres.setVisibility(View.GONE);
                cuatro.setVisibility(View.GONE);
            }else{
                numeroparadas.setText("3");
                tiempo.setText("30 minutos");
                uno.setText("No se baje en Calle La Laguna Nº1, continue hasta la siguiente parada");
                uno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uno.setChecked(true);
                    }
                });
                dos.setText("No se baje en Calle Las Peras, continue hasta la siguiente parada");
                dos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dos.setChecked(true);
                    }
                });
                tres.setText("Bajese en la parada Destino: " + datos_obt);
                tres.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tres.setChecked(true);
                    }
                });
                cuatro.setVisibility(View.GONE);
            }
        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.cancelar:
                startActivity(new Intent(Bienvenido.this,Inicio.class));
                break;
        }
    }
}
