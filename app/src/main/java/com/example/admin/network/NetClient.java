package com.example.admin.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetClient {
    private static String URL = "http://japi.juhe.cn/joke/";

    private APIService mComInterface;

    private OkHttpClient.Builder httpClient;

    private Retrofit.Builder builder;

    public static NetClient instance;

    public static NetClient getInstance(){
        if(instance==null){
            instance=new NetClient();
        }
        return instance;
    }


    private NetClient(){
        httpClient=new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new BaseInterceptor());
        build();
    }

    private void build(){
        builder =new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build());
    }


    public APIService getService() {
        mComInterface= builder.build().create(APIService.class);
        return mComInterface;
    }

}