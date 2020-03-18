package com.kkatia.behancer.ui.projects;

import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.utils.DateUtils;

import static com.kkatia.behancer.ui.projects.ProjectsHolder.FIRST_OWNER_INDEX;

public class ProjectsListItemViewModel {
    private String imageUrl;
    private String name;
    private String username;
    private String published;

    public ProjectsListItemViewModel(Project project){
        imageUrl=project.getCover().getPhotoUrl();
       name=project.getName();
       username=project.getOwners().get(FIRST_OWNER_INDEX).getUsername();
       published= DateUtils.format( project.getPublishedOn());
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPublished() {
        return published;
    }
}
