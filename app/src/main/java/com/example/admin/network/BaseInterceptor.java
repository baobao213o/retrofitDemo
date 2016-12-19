package com.example.admin.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Admin on 2016/12/9.
 */

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl url=original.url().newBuilder()
                .addQueryParameter("key", "232dd5d42598f9d3d4205d3c3ea13c8b")
                .build();

        Request request = original.newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .method(original.method(), original.body())
                .url(url)
                .build();
        return chain.proceed(request);
    }}
