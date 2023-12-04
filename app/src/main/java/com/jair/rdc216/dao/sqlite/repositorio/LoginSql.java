package com.jair.rdc216.dao.sqlite.repositorio;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jair.rdc216.dao.sqlite.model.LoginModel;

@Dao
public interface LoginSql {

    @Insert
    long insert(LoginModel login);
    @Update
    int update(LoginModel login);

    @Delete
    int delete(LoginModel login);

    @Query("SELECT * FROM login")  //returna todos os login
    LoginModel getLoginUsuario();

    @Query("SELECT * FROM login WHERE id = :id")  //returna o login pelo id
    LoginModel load(int id);

    @Query("SELECT * FROM login WHERE email = :email")  // return o login pelo email
    LoginModel load(String email);

    @Query("SELECT * FROM login WHERE email = :email AND password = :password")  // lista o login do usuario pelo id
    LoginModel load(String email,  String password);


}
