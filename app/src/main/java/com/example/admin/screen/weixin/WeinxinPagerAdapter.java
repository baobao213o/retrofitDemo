package com.example.admin.screen.weixin;


import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebView;

import java.util.ArrayList;

public class WeinxinPagerAdapter extends LoopVPAdapter<WebView> {

    private ArrayList<WebView> viewList;//数据源

    public WeinxinPagerAdapter(ArrayList<WebView> datas, ViewPager viewPager) {
        super(datas, viewPager);
    }

    public void setList(ArrayList<WebView> viewList){
        this.viewList = viewList;
    }

    @Override
    protected View getItemView(WebView data) {
        return data;
    }


}
