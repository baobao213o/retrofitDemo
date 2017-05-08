package com.example.admin.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetClient {
    private final static String URL = "http://japi.juhe.cn/";

    private Retrofit.Builder builder;

    public NetClient() {
        this(BaseInterceptor.JOKE_TYPE);
    }

    public NetClient(int type) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new BaseInterceptor(type));
        builder = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build());
    }

    public APIService getService() {
        return builder.build().create(APIService.class);
    }

}