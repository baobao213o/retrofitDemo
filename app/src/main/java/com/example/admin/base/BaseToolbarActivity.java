package com.example.admin.base;

import android.support.v7.widget.Toolbar;

import com.example.admin.screen.R;

import butterknife.BindView;

/**
 * Created by Admin on 2017/1/12.
 */

public abstract class BaseToolbarActivity extends BaseActivity{
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    public void setupView() {
        setSupportActionBar(toolbar);
    }
}
