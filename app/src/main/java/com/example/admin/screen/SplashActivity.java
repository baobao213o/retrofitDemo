package com.example.admin.screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.MotionEvent;

import com.example.admin.base.ui.BaseActivity;

import java.lang.ref.WeakReference;

public class SplashActivity extends BaseActivity {
    private static final int SHOW_TIME_MIN = 3000;
    private static long mStartTime;
    private static boolean isFirstUse;
    private boolean isLoadingFinished;
    private Handler mHandler = new SplashActivityHandler(this);


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        mStartTime = System.currentTimeMillis();
        startLoadingTask();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isLoadingFinished) {
                mHandler.sendMessage(mHandler.obtainMessage(2));
            }
        }

        return super.onTouchEvent(event);
    }

    private void startLoadingTask() {
        new LoadingTask().execute();
    }

    private class LoadingTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            isLoadingFinished = true;
            mHandler.sendMessage(mHandler.obtainMessage(1));
        }

        @Override
        protected Void doInBackground(Void... params) {
            SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            isFirstUse = preferences.getBoolean("isFirstUse", true);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class SplashActivityHandler extends Handler {
        WeakReference<SplashActivity> splashActivityWeakReference;

        SplashActivityHandler(SplashActivity splashActivity) {
            splashActivityWeakReference = new WeakReference<SplashActivity>(splashActivity);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    long loadingTime = System.currentTimeMillis() - mStartTime;
                    if (loadingTime < SHOW_TIME_MIN) {
                        postDelayed(switchActivity, SHOW_TIME_MIN - loadingTime);
                    } else {
                        post(switchActivity);
                    }
                    break;
                case 2:
                    if (switchActivity != null) {
                        removeCallbacks(switchActivity);
                    }
                    post(switchActivity);
                    break;
                default:
                    break;
            }
            removeMessages(1);
        }

        Runnable switchActivity = new Runnable() {

            @Override
            public void run() {
                if (isFirstUse) {
                    SplashActivity splashActivity = splashActivityWeakReference.get();
                    SharedPreferences preferences = splashActivity.getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isFirstUse", false);
                    editor.commit();
                    splashActivity.startActivity(new Intent(splashActivity, GuideActivity.class));
                    splashActivity.finish();
                } else {
                    SplashActivity splashActivity = splashActivityWeakReference.get();
                    splashActivity.startActivity(new Intent(splashActivity, MainActivity.class));
                    splashActivity.finish();
                }
            }
        };
    }
}
