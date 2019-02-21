package com.example.asus.busguideapp;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Dispositivos> {

    private List<Conexion.Dispo> dispositives;

    RVAdapter(List<Conexion.Dispo> dispositives){
        this.dispositives=dispositives;
    }

    static class Dispositivos extends RecyclerView.ViewHolder{
        CardView cv;
        @SuppressLint("StaticFieldLeak")
        static TextView name;
        @SuppressLint("StaticFieldLeak")
        static TextView direccion;

        Dispositivos(View itemView) {
            super(itemView);
            cv= itemView.findViewById(R.id.cv);
            name = itemView.findViewById(R.id.nombre_disp);
            direccion= itemView.findViewById(R.id.direccion_disp);
        }
    }

    @Override
    public int getItemCount(){
        return dispositives.size();
    }

    @NonNull
    @Override
    public Dispositivos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.connexion,viewGroup,false);
        return new Dispositivos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Dispositivos Dispositivos, int i) {
        RVAdapter.Dispositivos.name.setText(dispositives.get(i).nombre);
        RVAdapter.Dispositivos.direccion.setText(dispositives.get(i).direccion);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
