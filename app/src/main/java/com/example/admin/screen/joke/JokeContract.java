package com.example.admin.screen.joke;


import com.example.admin.base.mvp.BasePresenter;
import com.example.admin.base.mvp.BaseView;
import com.example.admin.entity.JokeBean;

import java.util.List;

/**
 * Created by Admin on 2016/12/12.
 */

public interface JokeContract {
    interface View extends BaseView<Presenter> {
        void showSuccess(List<JokeBean.Data> data);
        void showFail(String message);

    }

    interface Presenter extends BasePresenter {
        void onRefreshAnimationEnd();
    }
}
