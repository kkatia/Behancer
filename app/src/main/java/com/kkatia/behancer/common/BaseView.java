package com.kkatia.behancer.common;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {
    void showLoading();
    void hideLoading();
    void showError();
}
