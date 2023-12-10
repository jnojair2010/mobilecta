package com.jair.rdc216.dao.sqlite.repositorio;

import android.content.Context;

import com.jair.rdc216.dao.sqlite.model.ConsultorModel;
import com.jair.rdc216.model.Consultor;

public class ConsultorRespostorio {

    private ConsultorSql mSql;

    public ConsultorRespostorio(Context context){

            ConsultorDatabase db = ConsultorDatabase.getDataBases(context);
            this.mSql = db.consultor();
    }

    public boolean InserirConsultor(Consultor consultor){

        ConsultorModel mConsultor = new ConsultorModel();
        mConsultor.setNome(consultor.getNome());
        mConsultor.setSobre_nome(consultor.getSobre_nome());
        mConsultor.setEmail(consultor.getEmail());
        mConsultor.setData_cadastro(consultor.getData_cadastro());
        mConsultor.setEstado_consultor(true);

        return this.mSql.insert(mConsultor)>0;
    }

    public ConsultorModel consultarConsultor(){
        return this.mSql.getConsultor();
    }

    public ConsultorModel getConsultor(){
        return this.mSql.getConsultor();
    }


}
