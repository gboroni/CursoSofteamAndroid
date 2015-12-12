package com.cursoandroid.aula6;

import java.util.List;

/**
 * Created by boroni on 28/09/15.
 */
public class Singleton {

    private static Singleton instance;
//    private List<Local> locais;
    private int position = 0;
    List<Tipo> tipos;
    List<Local> locais;
    Tipo tipo_clicado;
    private String tipo_estabelecimento = "1";


    private Singleton() {

    }

    public Tipo getTipo_clicado() {
        return tipo_clicado;
    }

    public void setTipo_clicado(Tipo tipo_clicado) {
        this.tipo_clicado = tipo_clicado;
    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    public static void setInstance(Singleton instance) {
        Singleton.instance = instance;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    //    public List<Local> getLocais() {
//        return locais;
//    }

//    public void setLocais(List<Local> locais) {
//        this.locais = locais;
//    }


    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }

    public int getPosition() {
        return position == 0 ? 0 : position;
    }

    public String getTipo_estabelecimento() {
        return tipo_estabelecimento;
    }

    public void setTipo_estabelecimento(String tipo_estabelecimento) {
        this.tipo_estabelecimento = tipo_estabelecimento;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
