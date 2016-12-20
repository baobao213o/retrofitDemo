package com.example.admin.screen.splash;

import com.example.admin.base.mvp.BasePresenter;
import com.example.admin.base.mvp.BaseView;

/**
 * Created by Admin on 2016/12/20.
 */

public interface SplashContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void onActionUp();
    }
}
