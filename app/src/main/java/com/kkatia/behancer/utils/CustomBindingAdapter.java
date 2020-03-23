package com.kkatia.behancer.utils;

import android.widget.ImageView;

import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.data.model.project.RichProject;
import com.kkatia.behancer.ui.projects.ProjectsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CustomBindingAdapter {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.get().load(imageUrl).into(imageView);
    }

    @BindingAdapter({"bind:data", "bind:clickHandler"})
    public static void configureRecyclerView(RecyclerView recyclerView, PagedList<RichProject> projectList, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        ProjectsAdapter mProjectsAdapter = new ProjectsAdapter( onItemClickListener);
        mProjectsAdapter.submitList(projectList);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(mProjectsAdapter);
    }

    @BindingAdapter({"bind:refreshState", "bind:onRefresh"})
    public static void configureSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout, boolean isLoading, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {

        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(isLoading));
    }
}
