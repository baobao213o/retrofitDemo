package com.example.admin.screen.weixin;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.admin.entity.WeixinBean;
import com.example.admin.screen.R;

import java.util.ArrayList;
import java.util.LinkedList;

class WeinxinPagerAdapter extends PagerAdapter {

    private ArrayList<WeixinBean.Content> list;//数据源

    private LinkedList<View> mViewCache = null;  //删除的缓存

    private LayoutInflater mLayoutInflater = null;

    WeinxinPagerAdapter(Context context, ArrayList<WeixinBean.Content> datas) {
        this.list = datas;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mViewCache = new LinkedList<>();
    }

    public void setList(ArrayList<WeixinBean.Content> viewList) {
        this.list = viewList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ViewHolder viewHolder;
        View convertView ;
        if (mViewCache.size() == 0) {
            convertView = this.mLayoutInflater.inflate(R.layout.pageritem_weixin, null, false);

            WebView webview = (WebView) convertView.findViewById(R.id.webview);
            viewHolder = new ViewHolder();
            viewHolder.webview = webview;
            convertView.setTag(viewHolder);
        } else {
            convertView = mViewCache.removeFirst();
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.webview.getSettings().setAppCacheEnabled(true);
        viewHolder.webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        viewHolder.webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        viewHolder.webview.loadUrl(list.get(position).getUrl());

        container.addView(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return convertView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View contentView = (View) object;
        container.removeView(contentView);
        this.mViewCache.add(contentView);

    }

    private final class ViewHolder {
        WebView webview;
    }

}
