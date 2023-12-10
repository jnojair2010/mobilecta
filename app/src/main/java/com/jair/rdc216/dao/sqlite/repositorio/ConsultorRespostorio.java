package com.jair.rdc216.dao.sqlite.repositorio;

import android.content.Context;

import com.jair.rdc216.dao.sqlite.model.ConsultorModel;

public class ConsultorRespostorio {

    private ConsultorSql mSql;

    public ConsultorRespostorio(Context context){

            ConsultorDatabase db = ConsultorDatabase.getDataBases(context);
            this.mSql = db.consultor();
    }

    public boolean InserirConsultor(ConsultorModel consultor){
        return this.mSql.insert(consultor)>0;
    }

    public ConsultorModel consultarConsultor(){
        return this.mSql.getConsultor();
    }

    public ConsultorModel getConsultor(){
        return this.mSql.getConsultor();
    }


}
