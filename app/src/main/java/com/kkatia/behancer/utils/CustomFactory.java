package com.kkatia.behancer.utils;

import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.ui.projects.ProjectsAdapter;
import com.kkatia.behancer.ui.projects.ProjectsViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CustomFactory extends ViewModelProvider.NewInstanceFactory {
    private Storage mStorage;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;
 public    CustomFactory(Storage storage, ProjectsAdapter.OnItemClickListener onItemClickListener){
        mStorage=storage;
        mOnItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new ProjectsViewModel(mStorage,mOnItemClickListener);
    }
}
