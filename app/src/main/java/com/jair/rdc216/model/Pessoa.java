package com.jair.rdc216.model;

public class Pessoa {

    private String name;
    private String sobreNemo;
    private String diaNascimento;
    private String mesNascimento;
    private String anoNascimento;
    private String rg;
    private String cpf;

    private String nomePai;
    private String nomeMae;

    private Endereco endereco = new Endereco();

    public Pessoa() {

    }

    public Pessoa(String name, String sobreNemo, String diaNascimento, String mesNascimento, String anoNascimento, String rg, String cpf, String nomePai, String nomeMae) {
        this.name = name;
        this.sobreNemo = sobreNemo;
        this.diaNascimento = diaNascimento;
        this.mesNascimento = mesNascimento;
        this.anoNascimento = anoNascimento;
        this.rg = rg;
        this.cpf = cpf;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
    }


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
