package com.example.admin.screen;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.example.admin.base.ui.BaseActivity;
import com.example.admin.screen.joke.JokeActivity;
import com.example.admin.screen.picture.FunPicActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setupView() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void bindEvent() {

    }


    @OnClick(R.id.btn_joke)
    void onBtnJokeClick() {
        startActivity(new Intent(this, JokeActivity.class));
    }
    @OnClick(R.id.btn_pic)
    void onBtnPicClick() {
        startActivity(new Intent(this, FunPicActivity.class));
    }
}
