package com.alonsodiez.mascotaspracticasemana5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alonsodiez.mascotaspracticasemana5.adapter.MascotaAdaptador;
import com.alonsodiez.mascotaspracticasemana5.fragment.IMascotasFavoritasView;
import com.alonsodiez.mascotaspracticasemana5.pojo.Mascota;
import com.alonsodiez.mascotaspracticasemana5.presentador.IRecyclerViewMascotasFavoritasPresenter;
import com.alonsodiez.mascotaspracticasemana5.presentador.RecyclerViewMascotasFavoritasPresenter;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity implements IMascotasFavoritasView {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecyclerViewMascotasFavoritasPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBarFavoritos);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        presenter = new RecyclerViewMascotasFavoritasPresenter(this, getBaseContext());
    }

/*    public void inicializarListaMascotas() {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.img7, "Thunder", "Caballo", true, 9));
        mascotas.add(new Mascota(R.drawable.img3, "Sandokan", "Tigre", true, 8));
        mascotas.add(new Mascota(R.drawable.img8, "Pingu", "Pantera", true, 8));
        mascotas.add(new Mascota(R.drawable.img6, "Bokeh", "Águila", true, 4));
        mascotas.add(new Mascota(R.drawable.img4, "Bob", "Perro", true, 3));
    }
*/
    @Override
    public void generarLinearLayoutVertical() {
        //ahora de qué forma queremos mostrar los datos
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
