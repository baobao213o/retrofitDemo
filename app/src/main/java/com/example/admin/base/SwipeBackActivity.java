package com.example.admin.base;

import android.content.res.TypedArray;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.admin.screen.R;


public abstract class SwipeBackActivity<VM extends ViewDataBinding> extends BaseActivity<VM> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SwipeActivityTheme);
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        TypedArray a = getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.windowBackground
        });
        int background = a.getResourceId(0, 0);
        a.recycle();

        ViewGroup decor = (ViewGroup) getWindow().getDecorView();
        ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
        decorChild.setBackgroundResource(background);
        decor.removeView(decorChild);

        SwipeLayout layout=new SwipeLayout(this);
        layout.addView(decorChild);
        decor.addView(layout);
    }
}
