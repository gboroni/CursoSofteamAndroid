package com.cursoandroid.aula6;

/**
 * Created by boroni on 05/12/15.
 */
public class Tipo {
    private int id = 0;
    private String nome = "";

    public Tipo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
