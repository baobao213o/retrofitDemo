package com.example.admin.screen.joke;


import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.admin.base.BaseActivity;
import com.example.admin.entity.JokeBean;
import com.example.admin.network.NetClient;
import com.example.admin.rxjava.Transformer;
import com.example.admin.screen.R;
import com.example.admin.screen.databinding.ActivityJokeBinding;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class JokeActivity extends BaseActivity<ActivityJokeBinding>{

    @BindView(R.id.list)
    RecyclerView mListView;

    private JokeAdapter mAdapter;

    private int pagesize = 1;
    private int page = 1;
    private ArrayList<JokeBean.Data> mDataSet = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_joke;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setupView() {
        mAdapter = new JokeAdapter(this);
        mListView.setAdapter(mAdapter);

        getData();
    }

    private void getData(){
        new NetClient().getService().getJoke("desc", page + "", pagesize + "", String.valueOf(System.currentTimeMillis()).substring(0,10)).compose(Transformer.<JokeBean>retrofit())
                .subscribe(new Subscriber<JokeBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }
                    @Override
                    public void onNext(JokeBean value) {
                        List<JokeBean.Data> temp = value.getData();
                        mDataSet.addAll(temp);
                        mAdapter.setList(mDataSet);
                        mAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(JokeActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void bindEvent() {

    }

}
