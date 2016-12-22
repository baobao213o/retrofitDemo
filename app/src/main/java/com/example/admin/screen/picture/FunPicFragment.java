package com.example.admin.screen.picture;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.admin.base.ui.BaseFragment;
import com.example.admin.screen.R;
import com.example.admin.screen.picture.adapter.ItemAdapter;
import com.example.admin.screen.picture.entity.FunPicBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Admin on 2016/12/16.
 */

public class FunPicFragment extends BaseFragment implements FunPicContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    MaterialRefreshLayout refreshLayout;
    private ItemAdapter mAdapter;

    private FunPicContract.Presenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_funpic;
    }

    @Override
    public void initData() {
        mPresenter.start();
    }

    @Override
    public void setupView() {
        mAdapter = new ItemAdapter(this.getContext());
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        refreshLayout.finishRefresh();
        refreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void setPresent(FunPicContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void exit() {
        getActivity().finish();
    }

}
