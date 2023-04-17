package com.example.vetassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearPedido extends AppCompatActivity {

    // Crear un array con las opciones que pueda tener
    private String[] clientes = {"Surtidora del llano", "Distribuidora del llano", "PetCol"};
    private String[] sucursales = {"Norte", "Oriente", "Sur", "Occidente"};

    private Button Continuar;
    private Button AgregarItem;

    private EditText Usuario;
    private EditText Contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pedido);
        BaseDeDatos db = new BaseDeDatos(this);

        // Crear la lista desplegable para los clientes
        AutoCompleteTextView clientesAC = findViewById(R.id.cliente);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, clientes);
        clientesAC.setAdapter(adapter);

        // Crear la lista despligable para las sucursales
        AutoCompleteTextView sucursalesAC = findViewById(R.id.sucursal);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, sucursales);
        sucursalesAC.setAdapter(adapter2);

        // Relacionar los campos con los objetos del layout
        Usuario = findViewById(R.id.cliente);
        Contrasena = findViewById(R.id.sucursal);

        // Ir a la pagina donde se encuentra la tabla
        Continuar = findViewById(R.id.continuar);
        Continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearPedido.this, tablas.class);
                startActivity(intent);
                finish();
            }
        });

        // Ir a la pagina donde se encuentra la tabla
        AgregarItem = findViewById(R.id.agregaritem);
        AgregarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Leer el edittext de usuario y contraseña
                String Usu = Usuario.getText().toString();
                String Pass = Contrasena.getText().toString();

                // Agregar pedido
                db.agregarUsuarios(Usu,Pass);
                Toast.makeText(CrearPedido.this, "Registro agregado con exito", Toast.LENGTH_SHORT).show();

                // Limpiar campos
                Usuario.setText("");
                Contrasena.setText("");

            }
        });



    }
}