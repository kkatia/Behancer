package com.kkatia.behancer.ui.userprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkatia.behancer.R;
import com.kkatia.behancer.data.model.project.Project;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserProjectsAdapter extends RecyclerView.Adapter<UserProjectsHolder> {

    @NonNull
    private final List<Project> mProjects = new ArrayList<>();

    @NonNull
    @Override
    public UserProjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_projects, parent, false);
        return new UserProjectsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProjectsHolder holder, int position) {
        Project project = mProjects.get(position);
        holder.bind(project);
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    public void addData(List<Project> data, boolean isRefreshed) {
        if (isRefreshed) {
            mProjects.clear();
        }
        mProjects.addAll(data);
        notifyDataSetChanged();
    }

}
