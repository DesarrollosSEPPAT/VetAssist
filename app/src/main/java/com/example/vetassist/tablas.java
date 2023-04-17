package com.example.vetassist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class tablas extends AppCompatActivity {

    // Crear instancia de la base de datos
    private TableLayout mTabla;
    private ImageButton retroceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablas);

        // Relacionarla con el objeto visual
        mTabla = findViewById(R.id.tablaprueba);
        retroceso = findViewById(R.id.retroceder);
        retroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tablas.this, CrearPedido.class);
                startActivity(intent);
                finish();
            }
        });

        // Conectar a la base de datos
        BaseDeDatos db2 = new BaseDeDatos(this);
        SQLiteDatabase db = null;
        Cursor cursor = null;

        // Generar la conexion
        try{
            // Abrir la base de datos en modo lectura
            db = SQLiteDatabase.openDatabase(getDatabasePath("baseusuarios.db").getPath(), null, SQLiteDatabase.OPEN_READONLY);

            // Ejecutar la consulta
            cursor = db.query("usuarios", null, null, null, null, null, null);

            // Recorrer los resultados y agregar las filas a la tabla
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String user = cursor.getString(1);
                String pass = cursor.getString(2);

                TableRow row = new TableRow(this);

                TextView idTextView = new TextView(this);
                idTextView.setText(id);
                idTextView.setTextSize(18);
                row.addView(idTextView);

                EditText userTextView = new EditText(this);
                userTextView.setText(user);
                userTextView.setTextSize(18);
                row.addView(userTextView);

                EditText passTextView = new EditText(this);
                passTextView.setText(pass);
                passTextView.setTextSize(18);
                row.addView(passTextView);

                Button borraritem = new Button(this);
                borraritem.setTag(row);
                borraritem.setBackgroundResource(R.drawable.cancelar);
                borraritem.setTextSize(26);
                borraritem.setText("X");
                borraritem.setTextColor(0xFFFF0000);
                borraritem.setTypeface(null, Typeface.BOLD);
                borraritem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(tablas.this);
                        builder.setMessage("¿Estás seguro que deseas eliminar este elemento?")
                                .setTitle("Confirmación")
                                .setCancelable(false)
                                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id2) {
                                        // Acción a realizar si el usuario confirma
                                        TableRow row = (TableRow) v.getTag();
                                        mTabla.removeView(row);
                                        db2.borrarUsuario(id);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id2) {
                                        // Acción a realizar si el usuario cancela
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
                row.addView(borraritem);
                mTabla.addView(row);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la base de datos y el cursor
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }
}

    /*
    // Crear una instancia de la clase MiBaseDeDatos
    BaseDeDatos db = new BaseDeDatos(this);
    SQLiteDatabase dbsq = db.getReadableDatabase();

    // Obtener la tabla
    TableLayout tableLayout = findViewById(R.id.tablaprueba);

    // Obtener lista de usuarios
    public List<String[]> listausuarios = obtenerUsuarios();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablas);

        // Agregar encabezados de la tabla
        TableRow encabezados = new TableRow(this);
        TextView textViewUsuario = new TextView(this);
        TextView textViewContrasena = new TextView(this);
        textViewUsuario.setText("UsuarioT");
        textViewContrasena.setText("Contraseña");
        encabezados.addView(textViewUsuario);
        encabezados.addView(textViewContrasena);
        tableLayout.addView(encabezados);

        // Mostrar la tabla
        MostrarFilas();

    }

    public List<String[]> obtenerUsuarios() {
        List<String[]> usuarios = new ArrayList<>();
        String query = "SELECT usuario, contrasena FROM usuarios";
        Cursor cursor = dbsq.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String[] usuario = new String[2];
                usuario[0] = cursor.getString(0);
                usuario[1] = cursor.getString(1);
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return usuarios;
    }

    public void MostrarFilas(){

        // Agregar las filas con los usuarios
        for (String[] usuario : listausuarios) {
            TableRow fila = new TableRow(this);
            TextView textViewUsuario = new TextView(this);
            TextView textViewContrasena = new TextView(this);
            textViewUsuario.setText(usuario[0]);
            textViewContrasena.setText(usuario[1]);
            fila.addView(textViewUsuario);
            fila.addView(textViewContrasena);
            tableLayout.addView(fila);
        }

    }

}*/