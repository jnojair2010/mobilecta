package com.jair.rdc216.dao.sqlite.repositorio;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jair.rdc216.dao.sqlite.model.ConsultorModel;
import com.jair.rdc216.dao.sqlite.model.LoginModel;
@Dao
public interface ConsultorSql {

    @Insert
    long insert(ConsultorModel consultor);
    @Update
    int update(ConsultorModel consultor);

    @Delete
    int delete(ConsultorModel consultor);

    @Query("SELECT * FROM consultor")  //returna todos os consultores
    ConsultorModel getConsultor();

    @Query("SELECT * FROM consultor WHERE id = :id")  //returna o login pelo id
    ConsultorModel load(int id);

    @Query("SELECT * FROM consultor WHERE email = :email")  // return o login pelo email
    ConsultorModel load(String email);

    /*
    @Query("SELECT * FROM consultor WHERE email = :email AND password = :password")  // lista o login do usuario pelo id
    ConsultorModel load(String email,  String password);*/
}
