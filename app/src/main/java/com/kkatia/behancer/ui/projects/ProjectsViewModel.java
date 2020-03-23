package com.kkatia.behancer.ui.projects;

import com.kkatia.behancer.BuildConfig;
import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.data.model.project.ProjectResponse;
import com.kkatia.behancer.data.model.project.RichProject;
import com.kkatia.behancer.utils.ApiUtils;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectsViewModel extends ViewModel {
    private Disposable mDisposable;
    private Storage mStorage;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;
    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<Boolean>();
    private LiveData<PagedList<RichProject>> mProjects = new MutableLiveData<>();

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            updateProjects();
        }
    };

    public ProjectsViewModel(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mStorage = storage;

        mOnItemClickListener = onItemClickListener;

//        mProjects = mStorage.getProjectsLive();
        mProjects = mStorage.getProjectsPaged();
        updateProjects();
    }

//
//    public void loadProjects() {
//        mDisposable = ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
//                .doOnSuccess(response -> mStorage.insertProjects(response))
//                .onErrorReturn(throwable ->
//                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
//                .doFinally(() -> mIsLoading.postValue(false))
//                .subscribe(
//                        response -> {
//                            mIsErrorVisible.postValue(false);
//                            mProjects.postValue(response.getProjects());
//                            },
//                        throwable -> {
//                         mIsErrorVisible.postValue(true);
//                        }
//                );
//    }
//

    private void updateProjects() {
//        mDisposable = ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
        mDisposable = ApiUtils.getApiService().getProjects("dog")
                .map(ProjectResponse::getProjects)
                .doOnSuccess(response -> mIsErrorVisible.postValue(false)
                )
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .subscribe(
                        response -> {
                            mStorage.insertProjects(response);
                        },
                        throwable -> {
                            mIsErrorVisible.postValue(true);
                            boolean value = mProjects.getValue() == null || mProjects.getValue().size() == 0;
                            mIsErrorVisible.postValue(value);
//                                    ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null

                        }
                );
    }

    @Override
    public void onCleared() {
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    public LiveData<PagedList<RichProject>> getProjects() {
        return mProjects;
    }
}
