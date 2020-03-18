package com.kkatia.behancer.ui.userprojects;

import com.arellomobile.mvp.InjectViewState;
import com.kkatia.behancer.BuildConfig;
import com.kkatia.behancer.common.BasePresenter;
import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class UserProjectsPresenter  extends BasePresenter<UserProjectsView> {
    private UserProjectsView mView;
    private Storage mStorage;

    public UserProjectsPresenter(UserProjectsView projectsView,Storage storage){
        mStorage=storage;mView=projectsView;
    }
    public void getProjects(String username){
        mCompositeDisposable.add(
                ApiUtils.getApiService().getUserProjects(BuildConfig.API_QUERY)
                        .doOnSuccess(response -> mStorage.insertProjects(response))
                        .onErrorReturn(throwable ->
                                ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getUserProjects(username) : null)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> mView.showRefresh())
                        .doFinally(() ->mView.hideRefresh())
                        .subscribe(
                                response -> {
                                    mView.showProjects(response.getProjects());
                                },
                                throwable -> {
                                    mView.showError();
                                }));
    }
}
