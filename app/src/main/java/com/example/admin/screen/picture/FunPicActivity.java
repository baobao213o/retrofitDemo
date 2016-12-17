package com.example.admin.screen.picture;

import com.example.admin.base.ui.BaseActivity;
import com.example.admin.screen.R;

public class FunPicActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_funpic;
    }

    @Override
    public void init() {
        FunPicFragment fragment=new FunPicFragment();
        new FunPicPresenter(fragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.funpic_container,fragment).commit();
    }
}
