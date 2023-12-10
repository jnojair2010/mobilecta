package com.jair.rdc216.model;

import androidx.room.ColumnInfo;

public class Consultor  {
    private int id;
    private String nome;
    private String sobre_nome;
    private String cpf;
    private String registro;
    private String email;
    private boolean estado_consultor;
    private String data_cadastro;
    private String mensagemRetornoServidor;
    private int idConsultor;
    private String Funcao;
    private String dataRegistro;



    public Consultor(){
        super();

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

    public String getSobre_nome() {
        return sobre_nome;
    }

    public void setSobre_nome(String sobre_nome) {
        this.sobre_nome = sobre_nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstado_consultor() {
        return estado_consultor;
    }

    public void setEstado_consultor(boolean estado_consultor) {
        this.estado_consultor = estado_consultor;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getMensagemRetornoServidor() {
        return mensagemRetornoServidor;
    }

    public void setMensagemRetornoServidor(String mensagemRetornoServidor) {
        this.mensagemRetornoServidor = mensagemRetornoServidor;
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

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegisto) {
        this.dataRegistro = dataRegisto;
    }
}
