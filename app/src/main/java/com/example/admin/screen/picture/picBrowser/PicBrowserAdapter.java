package com.example.admin.screen.picture.picBrowser;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.entity.FunPicBean;
import com.example.admin.screen.R;
import com.example.admin.util.ImageLoader;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by baoba on 2017/8/21.
 */

public class PicBrowserAdapter extends PagerAdapter {

    private ArrayList<FunPicBean.Data> list;//数据源

    private LinkedList<View> mViewCache = null;  //删除的缓存

    private LayoutInflater mLayoutInflater = null;

    private Context context;

    PicBrowserAdapter(Context context, ArrayList<FunPicBean.Data> datas) {
        this.list = datas;
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mViewCache = new LinkedList<>();
    }

    public void setList(ArrayList<FunPicBean.Data> viewList) {
        this.list = viewList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ViewHolder viewHolder;
        View convertView;
        if (mViewCache.size() == 0) {
            convertView = this.mLayoutInflater.inflate(R.layout.pageritem_pic_brower, null, false);

            ImageView webview = (ImageView) convertView.findViewById(R.id.iv_picmain);
            viewHolder = new ViewHolder();
            viewHolder.imageView = webview;
            convertView.setTag(viewHolder);
        } else {
            convertView = mViewCache.removeFirst();
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String url = list.get(position).getUrl();
        if (url.contains(".gif") || (url.contains(".GIF"))) {
            System.out.println(url);
            ImageLoader.getInstance().loadGif(context,url,viewHolder.imageView);
        } else {
            ImageLoader.getInstance().loadPic(context,url,viewHolder.imageView);
        }
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
        ImageView imageView;
    }

}
