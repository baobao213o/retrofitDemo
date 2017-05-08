package com.example.admin.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class BaseInterceptor implements Interceptor {


    public final static int JOKE_TYPE=0;
    public final static int WEIXIN_TYPE=1;

    private String key;

    public BaseInterceptor(int type) {
        switch(type){
            case JOKE_TYPE:
                key="232dd5d42598f9d3d4205d3c3ea13c8b";
                break;
            case WEIXIN_TYPE:
                key="993d4d9e8635af3a9e70bfe9d27208a2";
                break;
            default:
                break;
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl url=original.url().newBuilder()
                .addQueryParameter("key", key)
                .build();

        Request request = original.newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .method(original.method(), original.body())
                .url(url)
                .build();
        return chain.proceed(request);
    }}
