package com.alonsodiez.mascotaspracticasemana5.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alonsodiez.mascotaspracticasemana5.DetalleMascota;
import com.alonsodiez.mascotaspracticasemana5.db.ConstructorMascotas;
import com.alonsodiez.mascotaspracticasemana5.pojo.Mascota;
import com.alonsodiez.mascotaspracticasemana5.R;

import java.util.ArrayList;

/**
 * Created by LOBEZNO on 04/08/2016.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    //Constructor.
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //asociamos la vista/layout a nuestro RecyclerView.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        //Seteamos los valores que trae cada uno de los objetos de la lista Mascotas.
        final Mascota mascota = mascotas.get(position);
        //Una vez tenemos el objeto, obtenemos cada parametro de cada mascota.
        mascotaViewHolder.imgFoto.setImageResource(mascota.getImgFoto());
        mascotaViewHolder.tvNombreCV.setText(mascota.getsNombre());
        mascotaViewHolder.tvFavorito.setText(String.valueOf(mascota.getnFavorito()));

        /*if(mascota.isbFavorito())
            mascotaViewHolder.tvFavorito.setText(new Integer(mascota.getnFavorito()).toString());
        else
            mascotaViewHolder.tvFavorito.setEnabled(mascota.isbFavorito());
        */

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, mascota.getsNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetalleMascota.class);
                intent.putExtra("imgFoto", mascota.getImgFoto());
                intent.putExtra("nombre", mascota.getsNombre());
                intent.putExtra("tipo", mascota.getsTipo());
                intent.putExtra("isFavorito", mascota.isbFavorito());
                intent.putExtra("favorito", String.valueOf(mascota.getnFavorito()));
                activity.startActivity(intent);
            }
        });

        mascotaViewHolder.ibHuesoBlanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "+1 - ".concat(mascota.getsNombre()), Toast.LENGTH_SHORT).show();
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);

                mascotaViewHolder.tvFavorito.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista.
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvFavorito;
        private ImageButton ibHuesoBlanco; //Favorito

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto         = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV      = (TextView) itemView.findViewById(R.id.tvNombreMascota);
            tvFavorito      = (TextView) itemView.findViewById(R.id.tvFavorito);
            ibHuesoBlanco   = (ImageButton) itemView.findViewById(R.id.btnHuesoBlanco);
        }
    }
}
