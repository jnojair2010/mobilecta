package com.jair.rdc216.model;

public class Consultor extends Pessoa {

    private int idConsultor;
    private String Funcao;
    private String registro;

    public Consultor(){
        super();

    }

    public Consultor(String name, String sobreNemo, String diaNascimento, String mesNascimento, String anoNascimento, String rg, String cpf, String nomePai, String nomeMae){
        super(name, sobreNemo, diaNascimento, mesNascimento, anoNascimento, rg, cpf, nomePai, nomeMae);
    }



    public int getIdConsultor() {
        return idConsultor;
    }

    public void setIdConsultor(int idConsultor) {
        this.idConsultor = idConsultor;
    }

    public String getFuncao() {
        return Funcao;
    }

    public void setFuncao(String funcao) {
        Funcao = funcao;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}
