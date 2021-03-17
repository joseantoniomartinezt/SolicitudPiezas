package com.example.solicitudpiezas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class pantalla1 extends AppCompatActivity {

    private ImageButton btnSiguiente;
    private EditText txtNombrePieza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla1);

        btnSiguiente = findViewById(R.id.btnSiguientePantalla1);

        txtNombrePieza = findViewById(R.id.txtPieza);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nombrePiezaIntent = new Intent(pantalla1.this,Pantalla2.class);

                Bundle extras = new Bundle();
                extras.putString("nombre",txtNombrePieza.getText().toString());

                nombrePiezaIntent.putExtras(extras);

                startActivity(nombrePiezaIntent);
            }
        });
    }

}