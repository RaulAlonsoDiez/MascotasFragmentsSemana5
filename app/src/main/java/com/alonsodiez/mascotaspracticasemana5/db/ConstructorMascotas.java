package com.alonsodiez.mascotaspracticasemana5.db;

import android.content.ContentValues;
import android.content.Context;

import com.alonsodiez.mascotaspracticasemana5.R;
import com.alonsodiez.mascotaspracticasemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by LOBEZNO on 18/08/2016.
 */
public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;
    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos() {

        BaseDatos db = new BaseDatos(context);
        insertarOchoMascotas(db);
        return db.obtenerTodasMascotas();
    }

    public void insertarOchoMascotas(BaseDatos db) {
        String nombre = "";

        ContentValues contentValues = new ContentValues();
        nombre = "Sandokan";
        if(!db.comprobarNombreBaseDatos(nombre)) {
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE, nombre);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_TIPO, "Tigre");
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO, 0);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FOTO, R.drawable.img3);

            db.insertarMascota(contentValues);
        }

        nombre = "Bob";
        if(!db.comprobarNombreBaseDatos(nombre)) {
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE, nombre);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_TIPO, "Perro");
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO, 0);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FOTO, R.drawable.img4);

            db.insertarMascota(contentValues);
        }

        nombre = "Pirata";
        if(!db.comprobarNombreBaseDatos(nombre)) {
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE, nombre);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_TIPO, "Loro");
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO, 0);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FOTO, R.drawable.img5);

            db.insertarMascota(contentValues);
        }

        nombre = "Bokeh";
        if(!db.comprobarNombreBaseDatos(nombre)) {
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE, nombre);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_TIPO, "Águila");
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO, 0);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FOTO, R.drawable.img6);

            db.insertarMascota(contentValues);
        }

        nombre = "Thunder";
        if(!db.comprobarNombreBaseDatos(nombre)) {
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE, nombre);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_TIPO, "Caballo");
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO, 0);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FOTO, R.drawable.img7);

            db.insertarMascota(contentValues);
        }

        nombre = "Pingu";
        if(!db.comprobarNombreBaseDatos(nombre)) {
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE, nombre);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_TIPO, "Pantera");
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO, 0);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FOTO, R.drawable.img8);

            db.insertarMascota(contentValues);
        }

        nombre = "Teddy";
        if(!db.comprobarNombreBaseDatos(nombre)) {
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE, nombre);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_TIPO, "Hámster");
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO, 0);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FOTO, R.drawable.img1);

            db.insertarMascota(contentValues);
        }

        nombre = "Paddy";
        if(!db.comprobarNombreBaseDatos(nombre)) {
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE, nombre);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_TIPO, "Conejo");
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO, 0);
            contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_FOTO, R.drawable.img2);

            db.insertarMascota(contentValues);
        }
    }

    public void darLikeMascota (Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_NUM_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
        db.actualizarCampoFavoritos(mascota.getId(), obtenerLikesMascota(mascota));
    }

    public int obtenerLikesMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<Mascota> obtenerDatosMascotasFavoritas() {

        BaseDatos db = new BaseDatos(context);
        insertarOchoMascotas(db);
        return db.obtenerCincoMascotasFavoritas();
    }
}
