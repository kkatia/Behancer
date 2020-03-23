package com.kkatia.behancer.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkatia.behancer.R;
import com.kkatia.behancer.data.Storage;
//import com.kkatia.behancer.databinding.ProfileBinding;
import com.kkatia.behancer.databinding.ProfileBinding;
import com.kkatia.behancer.utils.CustomBindingAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    public static final String PROFILE_KEY = "PROFILE_KEY";
    private ProfileViewModel mProfileViewModel;
    private String mUsername;
//    private DataBindingUtil profileBinding;

    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            Storage storage = ((Storage.StorageOwner) context).obtainStorage();
            mProfileViewModel = new ProfileViewModel(storage);

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        profileBinding = DataBindingUtil.inflate(inflater, R.layout.profile, container, false);
//       CustomBindingAdapter.configureView(mProfileViewModel);
        ProfileBinding binding = DataBindingUtil.inflate(inflater, R.layout.profile, container, false);
//        dataBinding.setViewModel(viewModel);
//        profileBinding.setProfilevm(mProfileViewModel);

//        ProfileBinding binding=DataBindingUtil.setContentView(this.getActivity(),R.layout.profile);
        binding.setProfileViewModel(mProfileViewModel);
        return binding.getRoot();
//        return DataBindingUtil.inflate(inflater,R.layout.profile,container,false).getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mUsername = getArguments().getString(PROFILE_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle(mUsername);
        }
        mProfileViewModel.loadProfile(mUsername);
//        profileBinding.executePendingBindings();
    }

    @Override
    public void onDetach() {
        mProfileViewModel.dispatchDetach();
        super.onDetach();
    }
}