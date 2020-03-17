package com.kkatia.behancer.ui.projects;

import com.kkatia.behancer.common.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class ProjectsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return ProjectsFragment.newInstance();
    }
}
