package com.example.admin.network;

import com.example.admin.base.entity.ResBaseModel;
import com.example.admin.screen.joke.entity.JokeBean;
import com.example.admin.screen.picture.entity.FunPicBean;

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
}
