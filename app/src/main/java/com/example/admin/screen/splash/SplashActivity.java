package com.example.admin.screen.splash;

import android.view.MotionEvent;

import com.example.admin.base.ui.BaseActivity;
import com.example.admin.screen.R;

public class SplashActivity extends BaseActivity {

    private SplashFragment fragmnet;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        fragmnet=new SplashFragment();
        new SplashPresenter(fragmnet);
        getSupportFragmentManager().beginTransaction().replace(R.id.splash_container,fragmnet).commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            fragmnet.onTouchUp();
        }
        return super.onTouchEvent(event);
    }

}
