package com.kkatia.behancer.ui.profile;

import com.kkatia.behancer.AppDelegate;
import com.kkatia.behancer.common.SingleFragmentActivity;
import com.kkatia.behancer.data.Storage;

import androidx.fragment.app.Fragment;

public class ProfileActivity extends SingleFragmentActivity implements Storage.StorageOwner {

    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    protected Fragment getFragment() {
        if (getIntent() != null) {
            return ProfileFragment.newInstance(getIntent().getBundleExtra(USERNAME_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");
    }

    @Override
    public Storage obtainStorage() {
        return ((AppDelegate) getApplicationContext()).getStorage();
    }


}



