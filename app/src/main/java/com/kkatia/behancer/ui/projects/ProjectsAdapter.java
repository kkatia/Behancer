package com.kkatia.behancer.ui.projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkatia.behancer.R;
import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.data.model.project.RichProject;
import com.kkatia.behancer.databinding.ProjectBinding;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

//public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsHolder> {
public class ProjectsAdapter extends PagedListAdapter<RichProject,ProjectsHolder> {

//    @NonNull
//    private final List<RichProject> mProjects;
    private final OnItemClickListener mOnItemClickListener;

    public ProjectsAdapter( OnItemClickListener onItemClickListener) {
super(CALLBACK);
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ProjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_projects, parent, false);

        ProjectBinding binding =
                ProjectBinding.inflate(inflater, parent, false);

        return new ProjectsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsHolder holder, int position) {
        RichProject project = getItem(position);
        if(project!=null){
        holder.bind(project, mOnItemClickListener);}
    }

//    @Override
//    public int getItemCount() {
//
//        return mProjects==null?0: mProjects.size();
//    }

private static final DiffUtil.ItemCallback<RichProject> CALLBACK=new DiffUtil.ItemCallback<RichProject>() {
    @Override
    public boolean areItemsTheSame(@NonNull RichProject oldItem, @NonNull RichProject newItem) {
        return oldItem.mProject.getId()==newItem.mProject.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull RichProject oldItem, @NonNull RichProject newItem) {
        return (Objects.equals(oldItem, newItem));
    }
};
    public interface OnItemClickListener {

        void onItemClick(String username);
    }
}
