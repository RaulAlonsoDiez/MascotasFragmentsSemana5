package com.alonsodiez.mascotaspracticasemana5.presentador;

import android.content.Context;

import com.alonsodiez.mascotaspracticasemana5.db.ConstructorMascotas;
import com.alonsodiez.mascotaspracticasemana5.fragment.IMascotasFavoritasView;
import com.alonsodiez.mascotaspracticasemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by LOBEZNO on 19/08/2016.
 */
public class RecyclerViewMascotasFavoritasPresenter implements IRecyclerViewMascotasFavoritasPresenter {

    private IMascotasFavoritasView iMascotasFavoritasView;
    private Context context;
    ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewMascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView, Context context) {
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        this.context = context;
        obtenerMascotasFavoritasBaseDatos();
    }

    @Override
    public void obtenerMascotasFavoritasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatosMascotasFavoritas();
        mostrarMascotasFavoritasRV();
    }

    @Override
    public void mostrarMascotasFavoritasRV() {
        iMascotasFavoritasView.inicializarAdaptadorRV(iMascotasFavoritasView.crearAdaptador(mascotas));
        iMascotasFavoritasView.generarLinearLayoutVertical();
    }
}
