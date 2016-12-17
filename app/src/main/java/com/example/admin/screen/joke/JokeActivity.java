package com.example.admin.screen.joke;


import com.example.admin.base.ui.BaseActivity;
import com.example.admin.screen.R;


public class JokeActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_joke;
    }

    @Override
    public void init() {
        JokeFragment fragment=new JokeFragment();
        new JokePresenter(fragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.joke_container,fragment).commit();
    }
}
