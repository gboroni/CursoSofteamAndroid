package com.cursoandroid.login;

/**
 * Created by boroni on 13/11/15.
 */
public class Usuario {
    private String login = "";
    private String senha = "";

    public Usuario(String senha, String login) {
        this.senha = senha;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean verificaLogin(String senha,String login){
        if (login.equals(this.login) && senha.equals(this.senha)){
            return true;
        }
        return false;


    }
}
