package com.kkatia.behancer.ui.projects;

import com.kkatia.behancer.BuildConfig;
import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectsViewModel extends ViewModel {
    private Disposable mDisposable;
    private Storage mStorage;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;
    private MutableLiveData<Boolean> mIsErrorVisible =new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> mIsLoading =new MutableLiveData<Boolean>();
   private MutableLiveData<List<Project>> mProjects=new MutableLiveData<>();

private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener=new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        loadProjects();
    }
};

    public ProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener){
        mStorage=storage;

        mOnItemClickListener = onItemClickListener;
        mProjects.setValue(new ArrayList<>());
        loadProjects();
    }
    public void loadProjects() {
        mDisposable = ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
                .doOnSuccess(responce -> mStorage.insertProjects(responce))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .subscribe(
                        response -> {
                            mIsErrorVisible.postValue(false);
                            mProjects.postValue(response.getProjects());
                            },
                        throwable -> {
                         mIsErrorVisible.postValue(true);
                        }
                );
    }
@Override
    public void onCleared(){
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }
    public MutableLiveData<Boolean> getIsErrorVisible(){
        return mIsErrorVisible;
    }
    public MutableLiveData<Boolean> getIsLoading(){
        return mIsLoading;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    public MutableLiveData<List<Project>> getProjects() {
        return mProjects;
    }
}
