package com.jair.rdc216.dao.sqlite.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="consultor")
public class ConsultorModel {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome")
    private String nome;

    @ColumnInfo(name = "sobre_nome")
    private String sobre_nome;

    @ColumnInfo(name = "cpf")
    private String cpf;

    @ColumnInfo(name = "registro")
    private String registro;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "estado_consultor")
    private boolean estado_consultor;

    @ColumnInfo(name = "data_cadastro")
    private String data_cadastro;

    @ColumnInfo(name = "mensagemRetornoServido")
    private String mensagemRetornoServidor;


    public ConsultorModel(){}

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
}
