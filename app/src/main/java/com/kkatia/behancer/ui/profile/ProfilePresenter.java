package com.kkatia.behancer.ui.profile;

import com.kkatia.behancer.BuildConfig;
import com.kkatia.behancer.common.BasePresenter;
import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter extends BasePresenter {
    private ProfileView mView;
    private Storage mStorage;

    public ProfilePresenter(ProfileView profileView,Storage storage){
        mStorage=storage;mView=profileView;
    }
    public void getProfile(String username){
        mCompositeDisposable.add(
                ApiUtils.getApiService().getUserInfo(BuildConfig.API_QUERY)
                        .doOnSuccess(response -> mStorage.insertUser(response))
                        .onErrorReturn(throwable ->
                                ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getUser(username) : null)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> mView.showRefresh())
                        .doFinally(() ->mView.hideRefresh())
                        .subscribe(
                                response -> {
                                    mView.showProfile(response.getUser());
                                },
                                throwable -> {
                                    mView.showError();
                                }));
    }
}
