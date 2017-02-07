package com.example.admin.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Admin on 2016/12/12.
 */

public abstract class BaseFragment extends AppCompatDialogFragment{
    private View view;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(getLayoutId(),null);
        unbinder=ButterKnife.bind(this,view);
        initData();
        setupView();
        bindEvent();
        return view;
    }

    public abstract int getLayoutId();
    public abstract void initData();
    public abstract void setupView();
    public abstract void bindEvent();


    public View getView(){
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getRefWatcher(getActivity()).watch(this);
    }
}
