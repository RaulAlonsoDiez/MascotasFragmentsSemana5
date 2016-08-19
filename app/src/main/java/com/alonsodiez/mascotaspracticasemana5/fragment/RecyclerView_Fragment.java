package com.alonsodiez.mascotaspracticasemana5.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alonsodiez.mascotaspracticasemana5.R;
import com.alonsodiez.mascotaspracticasemana5.adapter.MascotaAdaptador;
import com.alonsodiez.mascotaspracticasemana5.pojo.Mascota;
import com.alonsodiez.mascotaspracticasemana5.presentador.IRecyclerViewFragmentPresenter;
import com.alonsodiez.mascotaspracticasemana5.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by LOBEZNO on 12/08/2016.
 */
public class RecyclerView_Fragment extends Fragment implements IRecyclerViewFragmentView {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());

        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        //ahora de qué forma queremos mostrar los datos
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        //Creamos un objeto de MascotaAdaptador
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
