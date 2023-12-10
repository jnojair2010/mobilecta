package com.jair.rdc216.dao.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHttp {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitHttp(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.56.1:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static <S> S createService(Class<S> serviceClass){

        return getRetrofitHttp().create(serviceClass);
    }


}
