package com.example.admin.screen.main;

import com.example.admin.base.mvp.BasePresenter;
import com.example.admin.base.mvp.BaseView;
import com.example.admin.screen.main.entity.MainBean;

import java.util.ArrayList;

/**
 * Created by Admin on 2016/12/27.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showMainMenuData(ArrayList<MainBean> data);
    }

    interface Presenter extends BasePresenter {

    }
}