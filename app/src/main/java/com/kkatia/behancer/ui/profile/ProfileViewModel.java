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

public class ProfileViewModel  {

    private String imageUrl;
    private String profileName;
    private String profileCreatedOn;
    private String profileLocation;


    private Disposable mDisposable;
    private Storage mStorage;
    private ObservableBoolean mIsErrorVisible =new ObservableBoolean(false);
    private ObservableBoolean mIsLoading =new ObservableBoolean(false);

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadProfile(mProfile.get().getUsername());
        }
    };
    private ObservableField<User> mProfile = new ObservableField<User>();

    public User getProfile() {
        return mProfile.get();
    }


    public void setProfile(User value) {
     mProfile.set(value);
    }


    public ProfileViewModel(Storage storage){
        mStorage=storage;
    }
    public void loadProfile(String username) {
        mDisposable = ApiUtils.getApiService().getUserInfo(BuildConfig.API_QUERY)
                .doOnSuccess(responce -> mStorage.insertUser(responce))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getUser(username) : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(
                        response -> {
                            mProfile.set(response.getUser());
                            mIsErrorVisible.set(false);
                            bind(response.getUser());
                        },
                        throwable -> {
                            mIsErrorVisible.set(true);
                        }
                );
    }

    private void bind(User user) {
        imageUrl = user.getImage().getPhotoUrl();
        profileName = user.getDisplayName();
        profileCreatedOn = DateUtils.format(user.getCreatedOn());
        profileLocation = user.getLocation();
    }

    public void dispatchDetach(){
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
    public void onAttach(String user){
        loadProfile(user);
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfileCreatedOn() {
        return profileCreatedOn;
    }

    public String getProfileLocation() {
        return profileLocation;
    }
}
