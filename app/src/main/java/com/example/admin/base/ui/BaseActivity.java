package com.example.admin.base.ui;

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
        setContentView(getLayoutId());
        unbinder=ButterKnife.bind(this);

        init();
    }



    public abstract int getLayoutId();
    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        App.getRefWatcher(this).watch(this);
    }
}
