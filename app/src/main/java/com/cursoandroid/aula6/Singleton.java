package com.cursoandroid.aula6;

/**
 * Created by boroni on 28/09/15.
 */
public class Singleton {

    private static Singleton instance;
//    private List<Local> locais;
    private int position = 0;
    private String tipo_estabelecimento = "1";


    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

//    public List<Local> getLocais() {
//        return locais;
//    }

//    public void setLocais(List<Local> locais) {
//        this.locais = locais;
//    }

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
