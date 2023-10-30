package com.jair.rdc216.manager;

import com.jair.rdc216.model.Empresa;

import java.util.ArrayList;
import java.util.List;

public class ManagerRegraDeNegocio {

    private ManagerUsuarioSistema mManagerUsuarioSistema = new ManagerUsuarioSistema();
    private List<Empresa> mListaEmpresa = new ArrayList<>();


    public int getSizerListaEmpresa(){
        this.addListaEmpresa();

        return mListaEmpresa.size();
    }

    public void setListEmpresa(List<Empresa> mList){
        this.mListaEmpresa = mList;
    }

    private void addListaEmpresa(){
        Empresa empresa = new Empresa();
        Empresa empresa1 = new Empresa();
        empresa.setIdEmpresa(1);
        mListaEmpresa.add(empresa);
    }


    public ManagerUsuarioSistema getmManagerUsuarioSistema() {
        return mManagerUsuarioSistema;
    }

    public void setmManagerUsuarioSistema(ManagerUsuarioSistema mManagerUsuarioSistema) {
        this.mManagerUsuarioSistema = mManagerUsuarioSistema;
    }
}
