package com.alonsodiez.mascotaspracticasemana5.fragment;

import com.alonsodiez.mascotaspracticasemana5.adapter.MascotaAdaptador;
import com.alonsodiez.mascotaspracticasemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by LOBEZNO on 18/08/2016.
 */
public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
