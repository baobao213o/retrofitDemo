package com.example.admin.screen.main;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.C;
import com.example.admin.base.BaseActivity;
import com.example.admin.entity.MainBean;
import com.example.admin.screen.R;
import com.example.admin.screen.databinding.ActivityMainBinding;
import com.example.admin.screen.joke.JokeActivity;
import com.example.admin.screen.picture.FunPicActivity;
import com.example.admin.screen.weixin.WeixinActivity;
import com.example.admin.util.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.recycler_main)
    RecyclerView recyclerView;
    @BindView(R.id.tablayout_iv)
    ImageView tablayout_iv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MainAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setupView() {
        setSupportActionBar(toolbar);
        if (navView != null) {
            navView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }
        mAdapter = new MainAdapter(this);
        recyclerView.setAdapter(mAdapter);
        ImageLoader.getInstance().loadPic(this,C.mImages[4],tablayout_iv);
    }

    @Override
    public void bindEvent() {
        mAdapter.setOnItemLisenter(new MainAdapter.ItemListener() {
            @Override
            public void onItemClick(View v, int position, View iv) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, JokeActivity.class));
                        break;
                    case 1:
                        Intent it = new Intent(MainActivity.this, FunPicActivity.class);
                        if (Build.VERSION.SDK_INT >= 21) {
                            it.putExtra("position", position);
                            startActivity(it, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, new Pair<>(iv, "share")).toBundle());
                        } else {
                            startActivity(it);
                        }
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, WeixinActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData(){
        ArrayList<MainBean> mList=new ArrayList<>();
        MainBean bean=new MainBean();
        bean.setContent("按更新时间查询笑话");
        mList.add(bean);
        bean=new MainBean();
        bean.setContent("最新趣图");
        mList.add(bean);
        bean=new MainBean();
        bean.setContent("微信精选");
        mList.add(bean);
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}
