package com.example.vetassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class Vendedor extends AppCompatActivity {

    private ImageButton CrearPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);

        CrearPedido = findViewById(R.id.CrearPedido);

        CrearPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Vendedor.this, CrearPedido.class);
                startActivity(intent);
                finish();

            }
        });

    }
}