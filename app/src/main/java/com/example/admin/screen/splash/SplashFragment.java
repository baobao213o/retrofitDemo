package com.example.admin.screen.splash;

import com.example.admin.base.ui.BaseFragment;
import com.example.admin.screen.R;

/**
 * Created by Admin on 2016/12/20.
 */

public class SplashFragment extends BaseFragment implements SplashContract.View{

    private SplashContract.Presenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void initData() {
        mPresenter.start();
    }

    @Override
    public void setupView() {

    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void setPresent(SplashContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void exit() {
        getActivity().finish();
    }

    public void onTouchUp(){
        mPresenter.onActionUp();
    }
}
