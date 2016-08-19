package com.alonsodiez.mascotaspracticasemana5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleMascota extends AppCompatActivity {

    private ImageView imgFoto;
    private TextView tvNombre;
    private TextView tvTipoMascota;
    private TextView tvFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle parametros   = getIntent().getExtras();
        String nombre       = parametros.getString("nombre");
        String tipo         = parametros.getString("tipo");
        int foto            = parametros.getInt("imgFoto");
        boolean esFavorito  = parametros.getBoolean("isFavorito");
        String favoritoSI   = "La mascota ha sido valorada como Favorita ";
        String favoritoNO   = "La mascota no ha sido valorada como Favorita";
        String veces = " veces";
        String favorito     = "";
        if(esFavorito){
            favorito = favoritoSI.concat(parametros.getString("favorito")).concat(veces);
        } else {
            favorito = favoritoNO;
        }

        imgFoto     = (ImageView) findViewById(R.id.imgFoto);
        imgFoto.setImageResource(foto);

        tvNombre    = (TextView) findViewById(R.id.tvNombre);
        tvNombre.setText(nombre);

        tvTipoMascota    = (TextView) findViewById(R.id.tvTipoMascota);
        tvTipoMascota.setText(tipo);

        tvFavorito    = (TextView) findViewById(R.id.tvFavorito);
        tvFavorito.setText(favorito);
    }

    public void paginaFavoritos(View v){
        Intent intent = new Intent(this, MascotasFavoritas.class);
        startActivity(intent);
    }
}
