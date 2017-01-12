package com.example.admin.network;

import com.example.admin.entity.FunPicBean;
import com.example.admin.entity.JokeBean;
import com.example.admin.entity.ResBaseModel;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 2016/12/9.
 */

public interface APIService {
    @GET("content/list.from")
    Flowable<ResBaseModel<JokeBean>> getJoke(@Query("sort") String sort, @Query("page") String page, @Query("pagesize") String pagesize, @Query("time") String time);

    @GET("img/text.from")
    Flowable<ResBaseModel<FunPicBean>> getFunPic(@Query("page") String page, @Query("pagesize") String pagesize);

    @GET("weixin/query")
    Flowable<ResBaseModel<FunPicBean>> getWeixin(@Query("pno") String pno, @Query("ps") String ps, @Query("dtype") String dtype);
}
