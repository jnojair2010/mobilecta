package com.jair.rdc216.dao.http;

import com.jair.rdc216.dao.sqlite.model.ConsultorModel;
import com.jair.rdc216.dao.sqlite.model.LoginModel;
import com.jair.rdc216.model.DatasScheduling;
import com.jair.rdc216.model.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceHttp {

    @GET("/getAllScheduling")
    Call<List<DatasScheduling>> getAllScheduling(

    );

    @FormUrlEncoded
    @POST("/salvarEmailLoginSmartPhone/")
    Call<ConsultorModel> salvarEmailSmartPhone(
            @Field("email") String email,
            @Field("nome") String nome,
            @Field("sobre_nome") String sobreNome,
            @Field("data_cadastro") String data_cadastro
    );

    @FormUrlEncoded
    @POST("/salvarEmailLoginSmartPhone/")
    Call<Login> salvarNomeConsultor(
            @Field("nome") String nome,
            @Field("sobre_nome") String sobreNome
    );
}
