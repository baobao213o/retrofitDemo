package com.example.admin.screen.joke;


import com.example.admin.network.NetClient;
import com.example.admin.rxjava.Transformer;
import com.example.admin.entity.JokeBean;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baoba on 2016/12/12.
 */

public class JokePresenter implements JokeContract.Presenter {

    private JokeContract.View mView;
    private int pagesize = 1;
    private int page = 1;
    private ArrayList<JokeBean.Data> mDataSet = new ArrayList<>();


    public JokePresenter(JokeContract.View mView) {
        this.mView = mView;
        mView.setPresent(this);
    }

    @Override
    public void start() {

        NetClient.getInstance().getService().getJoke("desc", page + "", pagesize + "", String.valueOf(System.currentTimeMillis()).toString().substring(0,10)).compose(Transformer.<JokeBean>retrofit())
                .subscribe(new Subscriber<JokeBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }
                    @Override
                    public void onNext(JokeBean value) {
                        List<JokeBean.Data> temp = value.getData();
                        mDataSet.addAll(temp);
                        mView.showSuccess(mDataSet);
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showFail(e.toString());
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onRefreshAnimationEnd() {
        page++;
        start();
    }

}
