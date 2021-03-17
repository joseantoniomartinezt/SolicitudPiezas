package com.example.solicitudpiezas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Pantalla2 extends AppCompatActivity {

    //Creo las variables necesarias para poder coger los elementos de la vista
    private ImageButton btnAnterior;
    private Button btnTomarFoto,btnGaleria;
    private int PICK_IMAGE = 100;
    private GridView vistaLista;
    private Uri imagenUri;
    private List<Uri> lista = new ArrayList<>();
    private GridViewAdapter adaptador;

    //Inicializo las variables previamente creadas y controlo su evento
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla2);

        btnAnterior = findViewById(R.id.btnAnteriorPantalla2);

        btnTomarFoto = findViewById(R.id.btnTomarFoto);

        btnGaleria = findViewById(R.id.btnAnadirImagen);

        vistaLista = findViewById(R.id.vistaImagenes);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            String nombrePieza = parametros.getString("nombre");
        }

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pantalla2.this,pantalla1.class));
            }
        });

        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });

    }

    //Este metodo abre la camara para poder tomar una foto
    private void tomarFoto(){
        Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tomarFotoIntent.resolveActivity(getPackageManager()) != null){

            startActivityForResult(tomarFotoIntent,1);
        }
    }

    //Este metodo controla lo que sucedera una vez que se haya elegido o tomado una foto
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ClipData clipData = data.getClipData();

        if (requestCode == 1 && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            imagenUri = (Uri) extras.get("data");
            lista.add(imagenUri);

        }

        else if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            if(clipData == null){
                imagenUri = data.getData();
                lista.add(imagenUri);
            }else {
                for(int ind=0;ind<clipData.getItemCount();ind++){
                    lista.add(clipData.getItemAt(ind).getUri());
                }
            }

        }

        adaptador = new GridViewAdapter(this,lista);
        vistaLista.setAdapter(adaptador);

    }

    //Este metodo abre la galeria para poder elegir una foto de nuestra galeria
    private void abrirGaleria(){
        Intent galeriaIntent = new Intent();
        galeriaIntent.setType("image/*");
        galeriaIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galeriaIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(galeriaIntent,PICK_IMAGE);
    }

}