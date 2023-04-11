package com.example.vetassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InicioSesion extends AppCompatActivity {

    // Definimos como variables los campos de texto y los botones
    private EditText Usuario;
    private EditText Contrasena;
    private Button Acceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inicio_sesion);

        // Relacionar los campos con los objetos del layout
        Usuario = findViewById(R.id.usuario);
        Contrasena = findViewById(R.id.contrasena);
        Acceder = findViewById(R.id.iniciarsesion);

        Acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Capturar los valores de los campos de texto
                String Usu = Usuario.getText().toString();
                String Pass = Usuario.getText().toString();

                // Comprobar que los campos de usuario y contraseña esten almacenados en la abse de datos
                if (Usu.equals("Admin")) {
                    Intent intent = new Intent(InicioSesion.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(InicioSesion.this, "Bienvenido a VetAssist.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(InicioSesion.this, "Datos incorrectos. Comprueba que todo este bien escrito e intenta de nuevo.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}