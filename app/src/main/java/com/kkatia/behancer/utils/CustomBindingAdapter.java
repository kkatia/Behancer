package com.kkatia.behancer.utils;

import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.data.model.user.User;
import com.kkatia.behancer.ui.profile.ProfileViewModel;
import com.kkatia.behancer.ui.projects.ProjectsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CustomBindingAdapter {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView,String imageUrl){
        Picasso.get().load(imageUrl).into(imageView);
    }
    @BindingAdapter({"bind:data","bind:clickHandler"})
    public static void configureRecyclerView(RecyclerView recyclerView, List<Project> projectList, ProjectsAdapter.OnItemClickListener onItemClickListener){
        ProjectsAdapter    mProjectsAdapter = new ProjectsAdapter(projectList,onItemClickListener);
      recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(mProjectsAdapter);
    }

//    @BindingAdapter({"bind:profiledata"})
//    public static void configureView(ProfileViewModel profileViewModel){
//
//    }
////view.setLayoutManager(new LinearLayoutManager( view.getContext()));
//
//    }
    @BindingAdapter({"bind:refreshState","bind:onRefresh"})
    public static void configureSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout, boolean isLoading, SwipeRefreshLayout.OnRefreshListener onRefreshListener){

        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.post(()->swipeRefreshLayout.setRefreshing(isLoading));
    }
}
