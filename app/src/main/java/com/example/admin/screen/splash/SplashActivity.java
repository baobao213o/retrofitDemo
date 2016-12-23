package com.example.admin.screen.splash;

import android.content.Context;
import android.view.MotionEvent;

import com.example.admin.base.ui.BaseActivity;
import com.example.admin.screen.R;

public class SplashActivity extends BaseActivity implements SplashContract.View{

    private SplashContract.Presenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        new SplashPresenter(this);
        mPresenter.start();
    }

    @Override
    public void setupView() {

    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void setPresent(SplashContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }


    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mPresenter.onActionUp();
        }
        return super.onTouchEvent(event);
    }

}
