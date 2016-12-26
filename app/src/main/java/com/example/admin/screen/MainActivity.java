package com.example.admin.screen;

import android.content.Intent;

import com.example.admin.base.ui.BaseActivity;
import com.example.admin.screen.joke.JokeActivity;
import com.example.admin.screen.picture.FunPicActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setupView() {
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
