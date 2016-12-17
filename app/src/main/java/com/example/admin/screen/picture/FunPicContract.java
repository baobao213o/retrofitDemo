package com.example.admin.screen.picture;

import com.example.admin.base.mvp.BasePresenter;
import com.example.admin.base.mvp.BaseView;
import com.example.admin.screen.picture.entity.FunPicBean;

import java.util.List;

/**
 * Created by Admin on 2016/12/16.
 */

public interface FunPicContract {
    interface View extends BaseView<FunPicContract.Presenter> {
        void refreshFinish(List<FunPicBean.Data> data);
        void loadmoreFinish(List<FunPicBean.Data> data);
        void showFail(String message);
    }

    interface Presenter extends BasePresenter {
        void onLoadmore();
        void onRefesh();
    }
}
