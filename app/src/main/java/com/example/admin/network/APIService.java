package com.example.admin.network;

import com.example.admin.entity.FunPicBean;
import com.example.admin.entity.JokeBean;
import com.example.admin.entity.ResBaseModel;
import com.example.admin.entity.WeixinBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIService {
    @GET("joke/content/list.from")
    Flowable<ResBaseModel<JokeBean>> getJoke(@Query("sort") String sort, @Query("page") String page, @Query("pagesize") String pagesize, @Query("time") String time);

    @GET("joke/img/text.from")
    Flowable<ResBaseModel<FunPicBean>> getFunPic(@Query("page") String page, @Query("pagesize") String pagesize);

    @GET("http://v.juhe.cn/weixin/query")
    Flowable<ResBaseModel<WeixinBean>> getWeixin(@Query("pno") String pno, @Query("ps") String ps);
}
