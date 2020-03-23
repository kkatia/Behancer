package com.kkatia.behancer.ui.profile;

import com.kkatia.behancer.BuildConfig;
import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.data.model.user.User;
import com.kkatia.behancer.utils.ApiUtils;
import com.kkatia.behancer.utils.DateUtils;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel {

    private ObservableField<String> profileImageUrl = new ObservableField<String>();
    private ObservableField<String> profileName = new ObservableField<String>();
    private ObservableField<String> profileCreatedOn = new ObservableField<String>();
    private ObservableField<String> profileLocation = new ObservableField<String>();

    private String mUserName;

    private Disposable mDisposable;
    private Storage mStorage;
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadProfile();
        }
    };

    public ProfileViewModel(Storage storage, String username) {
        mStorage = storage;
        mUserName = username;
    }

    public void loadProfile() {
        mDisposable = ApiUtils.getApiService().getUserInfo(BuildConfig.API_QUERY)
                .doOnSuccess(responce -> mStorage.insertUser(responce))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getUser(mUserName) : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(
                        response -> {
                            mIsErrorVisible.set(false);
                            bind(response.getUser());
                        },
                        throwable -> {
                            mIsErrorVisible.set(true);
                        }
                );
    }

    private void bind(User user) {
        profileImageUrl.set(user.getImage().getPhotoUrl());
        profileName.set(user.getDisplayName());
        profileCreatedOn.set(DateUtils.format(user.getCreatedOn()));
        profileLocation.set(user.getLocation());
    }

    public void dispatchDetach() {
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ObservableBoolean getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    public ObservableField<String> getProfileImageUrl() {
        return profileImageUrl;
    }

    public ObservableField<String> getProfileName() {
        return profileName;
    }

    public ObservableField<String> getProfileCreatedOn() {
        return profileCreatedOn;
    }

    public ObservableField<String> getProfileLocation() {
        return profileLocation;
    }
}
