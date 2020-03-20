package com.kkatia.behancer.ui.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkatia.behancer.R;
import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.databinding.ProjectBinding;
import com.kkatia.behancer.databinding.ProjectsBinding;
import com.kkatia.behancer.ui.profile.ProfileActivity;
import com.kkatia.behancer.ui.profile.ProfileFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ProjectsFragment extends Fragment{
private ProjectsViewModel mProjectsViewModel;
private ProjectsAdapter.OnItemClickListener onItemClickListener=new ProjectsAdapter.OnItemClickListener() {
    @Override
    public void onItemClick(String username) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        Bundle args = new Bundle();
        args.putString(ProfileFragment.PROFILE_KEY, username);
        intent.putExtra(ProfileActivity.USERNAME_KEY, args);
        startActivity(intent);
    }
};
//    private RecyclerView mRecyclerView;
//    private View mErrorView;
//    private ProjectsAdapter mProjectsAdapter;

//    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
        Storage    mStorage = ((Storage.StorageOwner) context).obtainStorage();
       mProjectsViewModel=new ProjectsViewModel(mStorage,onItemClickListener);
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProjectsBinding projectBinding=      ProjectsBinding.inflate(inflater,container,false);
   projectBinding.setVm(mProjectsViewModel);
   return projectBinding.getRoot();
//        return inflater.inflate(R.layout.fr_projects, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            getActivity().setTitle(R.string.projects);
        }

         mProjectsViewModel.loadProjects();
    }


    @Override
    public void onDetach() {
       mProjectsViewModel.dispatchDetach();
        super.onDetach();
    }


}
