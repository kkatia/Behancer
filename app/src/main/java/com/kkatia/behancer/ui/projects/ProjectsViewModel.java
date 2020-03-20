package com.kkatia.behancer.ui.projects;

import com.kkatia.behancer.BuildConfig;
import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.utils.ApiUtils;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectsViewModel {
    private Disposable mDisposable;
    private Storage mStorage;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;
    private ObservableBoolean mIsErrorVisible =new ObservableBoolean(false);
    private ObservableBoolean mIsLoading =new ObservableBoolean(false);
   private ObservableArrayList<Project> mProjects=new ObservableArrayList<>();

private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener=new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        loadProjects();
    }
};

    public ProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener){
        mStorage=storage;

        mOnItemClickListener = onItemClickListener;
    }
    public void loadProjects() {
        mDisposable = ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
                .doOnSuccess(responce -> mStorage.insertProjects(responce))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(
                        response -> {
                            mIsErrorVisible.set(false);
                            mProjects.addAll(response.getProjects());
                            },
                        throwable -> {
                         mIsErrorVisible.set(true);
                        }
                );
    }

    public void dispatchDetach(){
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }
    public ObservableBoolean getIsErrorVisible(){
        return mIsErrorVisible;
    }
    public ObservableBoolean getIsLoading(){
        return mIsLoading;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    public ObservableArrayList<Project> getProjects() {
        return mProjects;
    }
}
