package com.alonsodiez.mascotaspracticasemana5.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alonsodiez.mascotaspracticasemana5.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by LOBEZNO on 18/08/2016.
 */
public class BaseDatos extends SQLiteOpenHelper{

    private static final int LIMITE_REGISTROS = 5;
    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE "         + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA + "(" +
                ConstantesBaseDatos.DATABASE_MASCOTA_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE     + " TEXT, " +
                ConstantesBaseDatos.DATABASE_MASCOTA_TIPO       + " TEXT, " +
                ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO   + " INTEGER, " +
                ConstantesBaseDatos.DATABASE_MASCOTA_FOTO       + " INTEGER" +
                ")";
        String queryCrearTablaMascotaLikes = "CREATE TABLE "            + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA_LIKES + "(" +
                ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_ID           + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_ID_MASCOTA   + " INTEGER, " +
                ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_NUM_LIKES    + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA + "(" + ConstantesBaseDatos.DATABASE_MASCOTA_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaMascotaLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA_LIKES);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()) {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setsNombre(registros.getString(1));
            mascotaActual.setsTipo(registros.getString(2));
            //mascotaActual.setnFavorito(registros.getInt(3));

            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_NUM_LIKES+") as likes" +
                    " FROM " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA_LIKES +
                    " WHERE " + ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_ID_MASCOTA + "=" + mascotaActual.getId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setnFavorito(registrosLikes.getInt(0));
            } else {
                mascotaActual.setnFavorito(0);
            }

            mascotaActual.setImgFoto(registros.getInt(4));
            mascotas.add(mascotaActual);
        }

        db.close();
        return mascotas;
    }

    public void insertarMascota (ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.DATABASE_TABLA_MASCOTA, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.DATABASE_TABLA_MASCOTA_LIKES, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;
        String query = "SELECT COUNT("+ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_NUM_LIKES+")" +
                " FROM " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA_LIKES +
                " WHERE " + ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_ID_MASCOTA + "=" + mascota.getId();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()) {
            likes = registros.getInt(0);
        }

        db.close();
        //System.out.println("QUERY OBTENERLIKESCONTACTO: "+query);
        return likes;
    }

    public ArrayList<Mascota> obtenerCincoMascotasFavoritas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA +
                " ORDER BY " + ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO + " DESC" +
                " LIMIT " + LIMITE_REGISTROS;

                SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()) {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setsNombre(registros.getString(1));
            mascotaActual.setsTipo(registros.getString(2));
            //mascotaActual.setnFavorito(registros.getInt(3));

            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_NUM_LIKES+") as likes" +
                    " FROM " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA_LIKES +
                    " WHERE " + ConstantesBaseDatos.DATABASE_MASCOTA_LIKES_ID_MASCOTA + "=" + mascotaActual.getId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setnFavorito(registrosLikes.getInt(0));
            } else {
                mascotaActual.setnFavorito(0);
            }

            mascotaActual.setImgFoto(registros.getInt(4));
            mascotas.add(mascotaActual);
        }

        db.close();
        return mascotas;
    }

    public boolean comprobarNombreBaseDatos(String nombre){
        String query = "SELECT * FROM " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA + " WHERE " +
                ConstantesBaseDatos.DATABASE_MASCOTA_NOMBRE + "='" + nombre + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    public void actualizarCampoFavoritos(int id, int favorito) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + ConstantesBaseDatos.DATABASE_TABLA_MASCOTA +
                " SET " + ConstantesBaseDatos.DATABASE_MASCOTA_FAVORITO + "=" + favorito +
                " WHERE " + ConstantesBaseDatos.DATABASE_MASCOTA_ID + "=" + id;
                ;
        db.execSQL(query);
    }
}
