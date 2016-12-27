package com.example.admin.screen;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.admin.base.ui.BaseActivity;
import com.example.admin.screen.main.MainActivity;

import java.lang.ref.WeakReference;

public class SplashActivity extends BaseActivity{

    private SwitchHandler mHandler = new SwitchHandler(Looper.getMainLooper(), this);

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    public void setupView() {

    }

    @Override
    public void bindEvent() {

    }

    class SwitchHandler extends Handler {
        private WeakReference<SplashActivity> mWeakReference;

        public SwitchHandler(Looper mLooper, SplashActivity activity) {
            super(mLooper);
            mWeakReference = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(i);
            //activity切换的淡入淡出效果
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            SplashActivity.this.finish();
        }
    }


}
