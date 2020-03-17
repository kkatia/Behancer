package com.kkatia.behancer.common;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter {
    protected CompositeDisposable mCompositeDisposable=new CompositeDisposable();
    public void disposeAll(){
        mCompositeDisposable.clear();
    }
}
