package com.kkatia.behancer.ui.profile;

import com.kkatia.behancer.common.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class ProfileActivity  extends SingleFragmentActivity {

    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    protected Fragment getFragment() {
        if (getIntent() != null) {
            return ProfileFragment.newInstance(getIntent().getBundleExtra(USERNAME_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");
    }
}

