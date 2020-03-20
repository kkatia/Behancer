package com.kkatia.behancer.ui.projects;

import com.kkatia.behancer.AppDelegate;
import com.kkatia.behancer.common.SingleFragmentActivity;
import com.kkatia.behancer.data.Storage;

import androidx.fragment.app.Fragment;

public class ProjectsActivity extends SingleFragmentActivity implements Storage.StorageOwner{

    @Override
    protected Fragment getFragment() {
        return ProjectsFragment.newInstance();
    }
    @Override
    public Storage obtainStorage() {
        return ((AppDelegate) getApplicationContext()).getStorage();
    }

}
