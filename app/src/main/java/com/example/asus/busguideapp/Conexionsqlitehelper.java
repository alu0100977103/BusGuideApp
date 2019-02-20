package com.example.asus.busguideapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asus.busguideapp.Utilidades.Utilidades;

public class Conexionsqlitehelper extends SQLiteOpenHelper {

    public Conexionsqlitehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //Crea la base de dato
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Genera la tabla
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        //Refresca scripts
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}
