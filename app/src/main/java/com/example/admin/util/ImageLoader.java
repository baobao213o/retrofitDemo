package com.example.admin.util;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.App;
import com.example.admin.screen.R;

public class ImageLoader {

    private Drawable drawable;

    private static volatile ImageLoader instance;

    private ImageLoader() {
        drawable = App.getmAppContext().getResources().getDrawable(R.color.background_dark);
    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    public void loadGif(Context context, String url, ImageView view) {
        //.placeholder(R.drawable.empty) .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(context).load(url).asGif().placeholder(drawable).error(R.drawable.erro).into(view);
    }

    public void loadPic(Context context, String url, ImageView view) {
        Glide.with(context).load(url).placeholder(drawable).error(R.drawable.erro).into(view);
    }
}
