package com.example.admin.screen.main;

import android.content.Intent;

import com.example.admin.base.BaseActivity;
import com.example.admin.screen.R;
import com.example.admin.screen.databinding.ActivitySplashBinding;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity<ActivitySplashBinding>{

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {

        Observable.timer(2, TimeUnit.SECONDS).compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(i);
                //activity切换的淡入淡出效果
                SplashActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                SplashActivity.this.finish();
            }
        });
    }

    @Override
    public void setupView() {
    }

    @Override
    public void bindEvent() {
    }

}
