package com.example.admin.screen.main;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.C;
import com.example.admin.adapter.MainAdapter;
import com.example.admin.base.ui.BaseToolbarActivity;
import com.example.admin.entity.MainBean;
import com.example.admin.screen.R;
import com.example.admin.screen.joke.JokeActivity;
import com.example.admin.screen.picture.FunPicActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseToolbarActivity implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.recycler_main)
    RecyclerView recyclerView;
    @BindView(R.id.tablayout_iv)
    ImageView tablayout_iv;

    private MainContract.Presenter mPresenter;


    private MainAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        new MainPresenter(this);

    }

    @Override
    public void setupView() {
        if (navView != null) {
            navView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }
        mAdapter = new MainAdapter(this);
        recyclerView.setAdapter(mAdapter);

        Glide.with(this).load(C.mImages[4]).diskCacheStrategy(DiskCacheStrategy.ALL).into(tablayout_iv);
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
                            startActivity(it, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, new Pair<>(iv, "share"), new Pair<>(v, "shared")).toBundle());
                        } else {
                            startActivity(it);
                        }
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
        mPresenter.start();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void setPresent(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMainMenuData(ArrayList<MainBean> data) {
        mAdapter.setList(data);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
