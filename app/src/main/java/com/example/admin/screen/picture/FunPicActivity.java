package com.example.admin.screen.picture;


import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.C;
import com.example.admin.base.BaseActivity;
import com.example.admin.entity.FunPicBean;
import com.example.admin.network.NetClient;
import com.example.admin.rxjava.Transformer;
import com.example.admin.screen.R;
import com.example.admin.screen.databinding.ActivityFunpicBinding;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FunPicActivity extends BaseActivity<ActivityFunpicBinding> {

    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    @BindView(R.id.tablayout_iv)
    ImageView tablayout_iv;
    private FunPicAdapter mAdapter;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;

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

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);

        recyclerView.setAdapter(mLRecyclerViewAdapter);

        getData();
    }

    private void getData(){
        new NetClient().getService().getFunPic( page + "", pagesize + "").compose(Transformer.<FunPicBean>retrofit())
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
                            recyclerView.refreshComplete(pagesize);
                            mAdapter.setList(mDataSet);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        }else{
                            recyclerView.refreshComplete(pagesize);
                            mAdapter.setList(mDataSet);
                            mLRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(FunPicActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        recyclerView.refreshComplete(pagesize);
                        mLRecyclerViewAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void bindEvent() {
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh=true;
                page=1;
                getData();
            }
        });


        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
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
