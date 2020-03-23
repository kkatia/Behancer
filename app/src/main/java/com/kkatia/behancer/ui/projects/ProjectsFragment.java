package com.kkatia.behancer.ui.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkatia.behancer.R;
import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.databinding.ProjectsBinding;
import com.kkatia.behancer.ui.profile.ProfileActivity;
import com.kkatia.behancer.ui.profile.ProfileFragment;
import com.kkatia.behancer.utils.CustomFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class ProjectsFragment extends Fragment {
    private ProjectsViewModel mProjectsViewModel;
    private ProjectsAdapter.OnItemClickListener onItemClickListener = new ProjectsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String username) {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            Bundle args = new Bundle();
            args.putString(ProfileFragment.PROFILE_KEY, username);
            intent.putExtra(ProfileActivity.USERNAME_KEY, args);
            startActivity(intent);
        }
    };

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            Storage mStorage = ((Storage.StorageOwner) context).obtainStorage();

            CustomFactory customFactory=new CustomFactory(mStorage,onItemClickListener);

            mProjectsViewModel = ViewModelProviders.of(this,customFactory).get(ProjectsViewModel.class);
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProjectsBinding projectBinding = ProjectsBinding.inflate(inflater, container, false);
        projectBinding.setVm(mProjectsViewModel);
        projectBinding.setLifecycleOwner(this);
        return projectBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            getActivity().setTitle(R.string.projects);
        }
    }
}
