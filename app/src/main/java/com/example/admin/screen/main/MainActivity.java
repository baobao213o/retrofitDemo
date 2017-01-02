package com.example.admin.screen.main;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.admin.base.ui.BaseActivity;
import com.example.admin.screen.R;
import com.example.admin.screen.main.adapter.MainAdapter;
import com.example.admin.screen.main.entity.MainBean;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,MainContract.View{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.recycler_main)
    RecyclerView recyclerView;

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
        setSupportActionBar(toolbar);
        if (navView != null) {
            navView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }
        mAdapter = new MainAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindEvent() {

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
        this.mPresenter=presenter;
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
}
