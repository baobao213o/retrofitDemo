package com.example.admin.rxjava;

import com.example.admin.base.entity.ResBaseModel;
import com.example.admin.network.ReturnCode;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Retrofit2  线程切换，flatMap检测Response model是否为null
 * Created by yubaokang on 2016/9/12.
 */
public class Transformer {

    public static <T> FlowableTransformer<T, T> ioMain() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> FlowableTransformer<ResBaseModel<T>, T> retrofit() {
        return new FlowableTransformer<ResBaseModel<T>, T>() {
            @Override
            public Publisher<T> apply(Flowable<ResBaseModel<T>> flowable) {
                return flowable
                        .flatMap(new Function<ResBaseModel<T>, Publisher<T>>() {
                            @Override
                            public Publisher<T> apply(ResBaseModel<T> t) throws Exception {
                                if (t == null || (t instanceof List && ((List) t).size() == 0)) {
                                    return Flowable.error(new ResponseNullException("response's model is null or response's list size is 0"));
                                } else {
                                    if (ReturnCode.ret_ok==t.getErrorCode()) {//如果返回时"0000"表示数据请求正常
                                        return  Flowable.just(t.getResult());
                                    } else {
                                        //如果网络未连接不会调用flatMap,所以网络未连接不会出现ServerException错误
                                        return Flowable.error(new ServerException(t.getErrorCode()+"", t.getReason()));//return the response's returnCode and msg
                                    }
                                }
                            }
                        })
                        .compose(Transformer.<T>ioMain());//线程切换
            }
        };
    }
}
