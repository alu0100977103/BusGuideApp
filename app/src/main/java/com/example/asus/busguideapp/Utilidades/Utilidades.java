package com.example.asus.busguideapp.Utilidades;

public class Utilidades {

    //Constantes campos tablas usuario
    public static final String TABLA_USUARIO="usuarios";
    public static final String CAMPO_NOMBRE="name";
    public static final String CAMPO_EMAIL="email";
    public static final String CAMPO_PASSWORD="password";


    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            " " + TABLA_USUARIO + "(" + CAMPO_NOMBRE + "TEXT, " +
            " " + CAMPO_EMAIL + "TEXT," +
            CAMPO_PASSWORD + "TEXT,";
    public static final String CAMPO_ID = "1";
}
