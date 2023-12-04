package com.jair.rdc216.model;


import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("id")
    private int id;

    @SerializedName("login")
    private String login;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("mensagemRetornoServidor")
    private String mensagemRetornoServidor;

    public Login(){}
    public Login(int id, String login, String email, String password, String mensagemRetornoServidor) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.mensagemRetornoServidor = mensagemRetornoServidor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMensagemRetornoServidor() {
        return mensagemRetornoServidor;
    }

    public void setMensagemRetornoServidor(String mensagemRetornoServidor) {
        this.mensagemRetornoServidor = mensagemRetornoServidor;
    }
}
