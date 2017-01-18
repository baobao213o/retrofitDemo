package com.example.admin.screen.main;


import com.example.admin.entity.MainBean;

import java.util.ArrayList;

/**
 * Created by Admin on 2016/12/27.
 */

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View mView;

    private ArrayList<MainBean> mList;

    @Override
    public void start() {

        mList=new ArrayList<>();
        MainBean bean=new MainBean();
        bean.setContent("按更新时间查询笑话");
        mList.add(bean);
        bean=new MainBean();
        bean.setContent("最新趣图");
        mList.add(bean);
        bean=new MainBean();
        bean.setContent("暂定");
        mList.add(bean);
        mView.showMainMenuData(mList);

    }

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        mView.setPresent(this);
    }


}
