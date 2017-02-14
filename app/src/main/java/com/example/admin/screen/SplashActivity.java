package com.example.admin.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.admin.base.BaseActivity;
import com.example.admin.screen.main.MainActivity;

import java.lang.ref.WeakReference;

public class SplashActivity extends BaseActivity{

    private SwitchHandler mHandler = new SwitchHandler(this);

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void setupView() {

    }

    @Override
    public void bindEvent() {

    }

    static class SwitchHandler extends Handler {
        private WeakReference<SplashActivity> mWeakReference;

        public SwitchHandler( SplashActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Activity activity = mWeakReference.get();
            if(activity==null){
                return;
            }
            Intent i = new Intent(activity, MainActivity.class);
            activity.startActivity(i);
            //activity切换的淡入淡出效果
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            activity.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mHandler=null;
    }
}
