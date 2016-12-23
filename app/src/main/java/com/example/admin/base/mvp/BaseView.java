package com.example.admin.base.mvp;

import android.content.Context;

/**
 * Created by Admin on 2016/12/12.
 */

public interface BaseView<T> {
    void setPresent(T presenter);
    void exit();
    Context getContext();
}
