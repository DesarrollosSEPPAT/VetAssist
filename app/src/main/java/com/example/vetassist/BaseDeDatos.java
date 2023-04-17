package com.example.vetassist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos extends SQLiteOpenHelper {

    // Definir el nombre y la version de la base de datos
    private static final String DATABASE_NAME = "baseusuarios.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public BaseDeDatos(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Metodos obligatorios de creacion y actualizacion
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (id INTEGER PRIMARY KEY, user TEXT, pass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }

    public void agregarUsuarios2() {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("id", "5");
        valores.put("user", "ghjg");
        valores.put("pass", "gjgj");
        db.insert("usuarios", null, valores);

        valores.clear();
        valores.put("id", "6");
        valores.put("user", "4sr");
        valores.put("pass", "422");
        db.insert("usuarios", null, valores);

        db.close();

    }

    public void agregarUsuarios(String usuario, String contrasena) {

        SQLiteDatabase db = getWritableDatabase();
        // Traer el mayor ID
        int maxID = 0;
        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM usuarios", null);
        if (cursor.moveToFirst()) {
            maxID = cursor.getInt(0) + 1;
        }
        cursor.close();

        ContentValues valores = new ContentValues();
        valores.put("id", Integer.toString(maxID));
        valores.put("user", usuario);
        valores.put("pass", contrasena);
        db.insert("usuarios", null, valores);
        db.close();
    }

    public boolean validarUsuario(String usuario, String contraseña) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE user=? AND pass=?", new String[]{usuario, contraseña});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void borrarUsuario(String id){

        SQLiteDatabase db = getReadableDatabase();
        db.delete("usuarios", "id = ?", new String[] { id });
        db.close();

    }

}
/*
    private static final String NOMBRE_BD = "usuarios.db";
    private static final int VERSION_BD = 1;
    private static final String TABLA_USUARIOS = "CREATE TABLE usuarios (usuario TEXT PRIMARY KEY, contraseña TEXT, rol TEXT)";

    public static String Validar = "";

    public BaseDeDatos(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }

    public void agregarUsuarios() {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("usuario", "juan");
        valores.put("contraseña", "1234");
        valores.put("rol", "admin");
        db.insert("usuarios", null, valores);

        valores.clear();
        valores.put("usuario", "maria");
        valores.put("contraseña", "5678");
        valores.put("rol", "usuario");
        db.insert("usuarios", null, valores);

        db.close();
    }

    public boolean validarUsuario(String usuario, String contraseña) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE usuario=? AND contraseña=?", new String[]{usuario, contraseña});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
*/