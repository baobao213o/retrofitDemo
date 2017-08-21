package com.example.admin.screen.picture.picBrowser;


import android.support.v4.view.ViewPager;

import com.example.admin.base.BaseActivity;
import com.example.admin.entity.FunPicBean;
import com.example.admin.screen.R;
import com.example.admin.screen.databinding.ActivityPicBrowserBinding;

import java.util.ArrayList;

import butterknife.BindView;

public class PicBrowserActivity extends BaseActivity<ActivityPicBrowserBinding> {

    @BindView(R.id.vp_pic_brower)
    ViewPager vpPicBrower;

    private int position;
    private ArrayList<FunPicBean.Data> data;

    private PicBrowserAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_pic_browser;
    }

    @Override
    public void initData() {
        position = getIntent().getIntExtra("position", 0);
        data = (ArrayList<FunPicBean.Data>) getIntent().getSerializableExtra("data");
        if (data != null) {
            adapter = new PicBrowserAdapter(this, data);
            vpPicBrower.setAdapter(adapter);
            vpPicBrower.setCurrentItem(position);
        }
    }

    @Override
    public void setupView() {

    }

    @Override
    public void bindEvent() {

    }
}
