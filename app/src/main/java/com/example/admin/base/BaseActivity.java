package com.example.admin.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.example.admin.App;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Admin on 2016/12/12.
 */

public abstract class BaseActivity<VM extends ViewDataBinding> extends RxAppCompatActivity{
    private Unbinder unbinder;
    private VM mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,getLayoutId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        unbinder= ButterKnife.bind(this);
        initData();
        setupView();
        bindEvent();
    }

    public VM getmBinding() {
        return mBinding;
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
