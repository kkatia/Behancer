package com.kkatia.behancer.ui.projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkatia.behancer.R;
import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.databinding.ProjectBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectsAdapter  extends RecyclerView.Adapter<ProjectsHolder> {

    @NonNull
    private final List<Project> mProjects;
    private final OnItemClickListener mOnItemClickListener;

    public ProjectsAdapter(List<Project> projects,OnItemClickListener onItemClickListener) {
       mProjects=projects;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ProjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_projects, parent, false);

        ProjectBinding binding=
        ProjectBinding.inflate(inflater,parent,false);

        return new ProjectsHolder(binding);
//        return new ProjectsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsHolder holder, int position) {
        Project project = mProjects.get(position);
        holder.bind(project, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }



    public interface OnItemClickListener {

        void onItemClick(String username);
    }
}
