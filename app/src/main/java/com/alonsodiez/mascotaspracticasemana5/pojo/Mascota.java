package com.alonsodiez.mascotaspracticasemana5.pojo;

/**
 * Created by LOBEZNO on 04/08/2016.
 */
public class Mascota {
    private int imgFoto;
    private String sNombre;
    private String sTipo;
    private boolean bFavorito;
    private int nFavorito;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mascota(int imgFoto, String sNombre, String sTipo, boolean bFavorito, int nFavorito) {
        this.imgFoto = imgFoto;
        this.sNombre = sNombre;
        this.sTipo = sTipo;
        this.bFavorito = bFavorito;
        this.nFavorito = nFavorito;
    }

    public Mascota(){

    }

    public int getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getsTipo() {
        return sTipo;
    }

    public void setsTipo(String sTipo) {
        this.sTipo = sTipo;
    }

    public boolean isbFavorito() {
        return bFavorito;
    }

    public void setbFavorito(boolean bFavorito) {
        this.bFavorito = bFavorito;
    }

    public int getnFavorito() {
        return nFavorito;
    }

    public void setnFavorito(int nFavorito) {
        this.nFavorito = nFavorito;
    }
}
