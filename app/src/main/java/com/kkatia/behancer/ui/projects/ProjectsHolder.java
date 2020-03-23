package com.kkatia.behancer.ui.projects;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kkatia.behancer.R;
import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.databinding.ProjectBinding;
import com.kkatia.behancer.utils.DateUtils;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

public class ProjectsHolder extends RecyclerView.ViewHolder {

    static final int FIRST_OWNER_INDEX = 0;

    private ProjectBinding mProjectBinding;

    public ProjectsHolder(ProjectBinding binding) {
        super(binding.getRoot());
        mProjectBinding=binding;
    }

    public void bind(Project item, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mProjectBinding.setProject(new ProjectsListItemViewModel(item));
        mProjectBinding.setOnItemClickListener(onItemClickListener);
        mProjectBinding.executePendingBindings();
    }
}
