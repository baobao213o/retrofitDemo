package com.example.admin.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Admin on 2016/12/12.
 */

public abstract class BaseActivity extends AppCompatActivity{
    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
        setContentView(getLayoutId());
        unbinder= ButterKnife.bind(this);
        initData();
        setupView();
        bindEvent();
    }


    public abstract int getLayoutId();
    public abstract void initData();
    public abstract void setupView();
    public abstract void bindEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        App.getRefWatcher(this).watch(this);
    }
}
