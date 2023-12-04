package com.jair.rdc216.dao.sqlite.repositorio;

import android.content.Context;

import com.jair.rdc216.dao.sqlite.model.LoginModel;

public class LoginRepositorio {

    private LoginSql mSql;

    public  LoginRepositorio(Context context){
        LoginDataBases db = LoginDataBases.getDataBases(context);
        this.mSql = db.login();
    }

    public boolean InserirLogin(LoginModel login){
        return this.mSql.insert(login)>0;
    }

    public LoginModel getLogin(){return this.mSql.getLoginUsuario();}

}
