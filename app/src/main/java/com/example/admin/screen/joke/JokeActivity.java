package com.example.admin.screen.joke;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.example.admin.base.ui.BaseActivity;
import com.example.admin.screen.R;
import com.example.admin.screen.joke.adapter.ItemAdapter;
import com.example.admin.screen.joke.adapter.SampleItemAnimator;
import com.example.admin.screen.joke.entity.JokeBean;
import com.race604.flyrefresh.FlyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class JokeActivity extends BaseActivity implements FlyRefreshLayout.OnPullRefreshListener, JokeContract.View{

    @BindView(R.id.list)
    RecyclerView mListView;
    @BindView(R.id.fly_layout)
    FlyRefreshLayout mFlylayout;

    private JokeContract.Presenter mPresenter;
    private ItemAdapter mAdapter;

    private Handler mHandler = new Handler();

    @Override
    public int getLayoutId() {
        return R.layout.activity_joke;
    }

    @Override
    public void initData() {
        new JokePresenter(this);
        mPresenter.start();
    }

    @Override
    public void setupView() {

        mFlylayout.setOnPullRefreshListener(this);

        mAdapter = new ItemAdapter(this);
        mListView.setAdapter(mAdapter);

        mListView.setItemAnimator(new SampleItemAnimator());
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
    public Context getContext() {
        return this;
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
        mPresenter.onRefreshAnimationEnd();
    }

    @Override
    public void setPresent(JokeContract.Presenter present) {
        mPresenter = present;
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void showSuccess(List<JokeBean.Data> data) {
        mAdapter.setList((ArrayList<JokeBean.Data>) data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
