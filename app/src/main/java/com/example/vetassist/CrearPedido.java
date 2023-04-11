package com.example.vetassist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class CrearPedido extends AppCompatActivity {

    // Crear un array con las opciones que pueda tener
    private String[] clientes = {"Surtidora del llano", "Distribuidora del llano", "PetCol"};
    private String[] sucursales = {"Norte", "Oriente", "Sur", "Occidente"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_crear_pedido);

        // Crear la lista desplegable para los clientes
        AutoCompleteTextView clientesAC = findViewById(R.id.cliente);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, clientes);
        clientesAC.setAdapter(adapter);

        // Crear la lista despligable para las sucursales
        AutoCompleteTextView sucursalesAC = findViewById(R.id.sucursal);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, sucursales);
        sucursalesAC.setAdapter(adapter2);

    }
}