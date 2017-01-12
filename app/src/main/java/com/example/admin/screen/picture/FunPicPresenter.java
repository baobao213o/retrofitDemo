package com.example.admin.screen.picture;


import com.example.admin.entity.FunPicBean;
import com.example.admin.network.NetClient;
import com.example.admin.rxjava.Transformer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/12/16.
 */

public class FunPicPresenter implements FunPicContract.Presenter{

    private FunPicContract.View mView;
    private int pagesize = 10;
    private int page = 1;
    private boolean isRefresh=true;
    private ArrayList<FunPicBean.Data> mDataSet = new java.util.ArrayList<>();


    public FunPicPresenter(FunPicContract.View mView) {
        this.mView = mView;
        mView.setPresent(this);
    }

    @Override
    public void start() {
        NetClient.getInstance().getService().getFunPic( page + "", pagesize + "").compose(Transformer.<FunPicBean>retrofit())
                .subscribe(new Subscriber<FunPicBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }
                    @Override
                    public void onNext(FunPicBean value) {
                        List<FunPicBean.Data> temp = value.getData();
                        if(isRefresh){
                            mDataSet.clear();
                        }
                        mDataSet.addAll(temp);
                        if(isRefresh){
                            mView.refreshFinish(mDataSet);
                        }else{
                            mView.loadmoreFinish(mDataSet);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showFail(e.getMessage());
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onLoadmore() {
        isRefresh=false;
        page++;
        start();
    }

    @Override
    public void onRefesh() {
        isRefresh=true;
        page=1;
        start();
    }


}
