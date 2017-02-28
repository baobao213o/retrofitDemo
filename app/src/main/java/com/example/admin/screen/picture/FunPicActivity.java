package com.example.admin.screen.picture;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.admin.C;
import com.example.admin.base.BaseActivity;
import com.example.admin.entity.FunPicBean;
import com.example.admin.network.NetClient;
import com.example.admin.rxjava.Transformer;
import com.example.admin.screen.R;
import com.example.admin.screen.databinding.ActivityFunpicBinding;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FunPicActivity extends BaseActivity<ActivityFunpicBinding> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    MaterialRefreshLayout refreshLayout;
    @BindView(R.id.tablayout_iv)
    ImageView tablayout_iv;
    private FunPicAdapter mAdapter;

    private int pagesize = 10;
    private int page = 1;
    private boolean isRefresh=true;
    private ArrayList<FunPicBean.Data> mDataSet = new java.util.ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_funpic;
    }

    @Override
    public void initData() {
        Glide.with(this).load(C.mImages[getIntent().getIntExtra("position",0)]).diskCacheStrategy(DiskCacheStrategy.ALL).into(tablayout_iv);
    }

    @Override
    public void setupView() {
        mAdapter = new FunPicAdapter(this);
        recyclerView.setAdapter(mAdapter);

        getData();
    }

    private void getData(){
        NetClient.getInstance().getService().getFunPic( page + "", pagesize + "").compose(Transformer.<FunPicBean>retrofit())
                .subscribe(new Subscriber<FunPicBean>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }
                    @Override
                    public void onNext(FunPicBean value) {
                        List<FunPicBean.Data> temp = value.getData();
                        if(isRefresh){
                            mDataSet.clear();
                        }
                        mDataSet.addAll(temp);
                        if(isRefresh){
                            mAdapter.setList(mDataSet);
                            mAdapter.notifyDataSetChanged();
                            refreshLayout.finishRefresh();
                        }else{
                            mAdapter.setList(mDataSet);
                            mAdapter.notifyDataSetChanged();
                            refreshLayout.finishRefreshLoadMore();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(FunPicActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        refreshLayout.finishRefresh();
                        refreshLayout.finishRefreshLoadMore();
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void bindEvent() {
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isRefresh=true;
                page=1;
                getData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                isRefresh=false;
                page++;
                getData();
            }
        });
        mAdapter.setOnItemLisenter(new FunPicAdapter.ItemListener() {

            @Override
            public void onItemClick(View iv, String url) {
                
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
