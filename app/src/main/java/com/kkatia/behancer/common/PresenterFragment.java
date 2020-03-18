package com.kkatia.behancer.common;

public abstract class PresenterFragment extends MyMvpAppCompatFragment{
    protected abstract BasePresenter getPresenter();
    @Override
    public void onDetach() {

        if(getPresenter()!=null){
            getPresenter().disposeAll();
        }
        super.onDetach();
    }
}
