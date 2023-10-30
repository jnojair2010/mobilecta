package com.jair.rdc216.dao.http;

import com.jair.rdc216.model.DatasScheduling;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceHttp {

    @GET("/getAllScheduling")
    Call<List<DatasScheduling>> getAllScheduling(

    );
}
