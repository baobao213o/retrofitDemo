package com.example.admin.screen.joke;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.example.admin.base.BaseActivity;
import com.example.admin.entity.JokeBean;
import com.example.admin.network.NetClient;
import com.example.admin.rxjava.Transformer;
import com.example.admin.screen.R;
import com.example.admin.screen.databinding.ActivityJokeBinding;
import com.example.admin.util.SampleItemAnimator;
import com.race604.flyrefresh.FlyRefreshLayout;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class JokeActivity extends BaseActivity<ActivityJokeBinding> implements FlyRefreshLayout.OnPullRefreshListener{

    @BindView(R.id.list)
    RecyclerView mListView;
    @BindView(R.id.fly_layout)
    FlyRefreshLayout mFlylayout;

    private JokeAdapter mAdapter;

    private Handler mHandler = new Handler();

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

        mFlylayout.setOnPullRefreshListener(this);

        mAdapter = new JokeAdapter(this);
        mListView.setAdapter(mAdapter);

        mListView.setItemAnimator(new SampleItemAnimator());

        getData();
    }

    private void getData(){
        NetClient.getInstance().getService().getJoke("desc", page + "", pagesize + "", String.valueOf(System.currentTimeMillis()).toString().substring(0,10)).compose(Transformer.<JokeBean>retrofit())
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
        View actionButton = mFlylayout.getHeaderActionButton();
        if (actionButton != null) {
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFlylayout.startRefresh();
                }
            });
        }

    }

    @Override
    public void onRefresh(FlyRefreshLayout view) {
        View child = mListView.getChildAt(0);
        if (child != null) {
            bounceAnimateView(child.findViewById(R.id.icon));
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFlylayout.onRefreshFinish();
            }
        }, 2000);
    }

    private void bounceAnimateView(View view) {
        if (view == null) {
            return;
        }
        Animator swing = ObjectAnimator.ofFloat(view, "rotationX", 0, 30, -20, 0);
        swing.setDuration(400);
        swing.setInterpolator(new AccelerateInterpolator());
        swing.start();
    }

    @Override
    public void onRefreshAnimationEnd(FlyRefreshLayout view) {
        page++;
        getData();
    }

}
