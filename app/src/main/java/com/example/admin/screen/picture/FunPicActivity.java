package com.example.admin.screen.picture;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.admin.C;
import com.example.admin.adapter.FunPicAdapter;
import com.example.admin.base.ui.BaseActivity;
import com.example.admin.entity.FunPicBean;
import com.example.admin.screen.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FunPicActivity extends BaseActivity implements FunPicContract.View{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    MaterialRefreshLayout refreshLayout;
    @BindView(R.id.tablayout_iv)
    ImageView tablayout_iv;
    private FunPicAdapter mAdapter;

    private FunPicContract.Presenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_funpic;
    }

    @Override
    public void initData() {
        new FunPicPresenter(this);
        mPresenter.start();
        Glide.with(this).load(C.mImages[getIntent().getIntExtra("position",0)]).diskCacheStrategy(DiskCacheStrategy.ALL).into(tablayout_iv);
    }

    @Override
    public void setupView() {
        mAdapter = new FunPicAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindEvent() {
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mPresenter.onRefesh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                mPresenter.onLoadmore();
            }
        });

    }


    @Override
    public void refreshFinish(List<FunPicBean.Data> data) {
        mAdapter.setList((ArrayList<FunPicBean.Data>) data);
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
    }

    @Override
    public void loadmoreFinish(List<FunPicBean.Data> data) {
        mAdapter.setList((ArrayList<FunPicBean.Data>) data);
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void showFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        refreshLayout.finishRefresh();
        refreshLayout.finishRefreshLoadMore();
    }
    @Override
    public Context getContext() {
        return this;
    }
    @Override
    public void setPresent(FunPicContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void exit() {
        finish();
    }

}
