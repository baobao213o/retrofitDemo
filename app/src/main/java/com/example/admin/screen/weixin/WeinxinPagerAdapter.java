package com.example.admin.screen.weixin;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.ArrayList;

public class WeinxinPagerAdapter extends PagerAdapter {

    private ArrayList<WebView> viewList;//数据源

    public void setList(ArrayList<WebView> viewList){
        this.viewList = viewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));//千万别忘记添加到container
        return viewList.get(position);
    }

    @Override
    public int getCount() {
        return viewList==null?0:viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
