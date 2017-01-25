package com.example.admin.rxjava;

import android.content.Context;

import com.example.admin.network.ProgressHandler;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


public class ProgressSubscribe<T> implements ProgressHandler.ProgressCancelListener, Subscriber<T> {

    //接口，用来回调
    public interface SubscribeOnNextListener<T> {
        void onNext(T t);
        void onError(Throwable e);
    }

    private SubscribeOnNextListener mOnNextListener;
    private ProgressHandler mProgressHandler;

    private Subscription subscription;

    public ProgressSubscribe(SubscribeOnNextListener listener, Context context){
        this.mOnNextListener = listener;
        mProgressHandler = new ProgressHandler(context,this,true);
    }

    private void showProgressDialog(){
        if(mProgressHandler!=null){
            mProgressHandler.obtainMessage(ProgressHandler.SHOW_PROGRESS).sendToTarget();
        }
    }
    private void disMissProgressDialog(){
        if(mProgressHandler!=null){
            mProgressHandler.obtainMessage(ProgressHandler.DISMISS_PROGRESS).sendToTarget();
            mProgressHandler = null;
        }
    }

    @Override
    public void onSubscribe(Subscription s) {
        subscription=s;
        showProgressDialog();
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(Throwable e) {
        disMissProgressDialog();
        mOnNextListener.onError(e);
    }

    @Override
    public void onComplete() {
        disMissProgressDialog();
    }

    @Override
    public void onNext(T value) {
        mOnNextListener.onNext(value);//回调MainActivity中的onNext方法
    }

    @Override
    public void onProgressCanceled() {//取消请求
        subscription.cancel();
    }
}