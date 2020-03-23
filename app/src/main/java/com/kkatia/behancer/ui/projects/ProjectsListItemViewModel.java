package com.kkatia.behancer.ui.projects;

import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.data.model.project.RichProject;
import com.kkatia.behancer.utils.DateUtils;

import static com.kkatia.behancer.ui.projects.ProjectsHolder.FIRST_OWNER_INDEX;

public class ProjectsListItemViewModel {
    private String imageUrl;
    private String name;
    private String username;
    private String published;

    public ProjectsListItemViewModel(RichProject project){
        imageUrl=project.mProject.getCover().getPhotoUrl();
       name=project.mProject.getName();
        published= DateUtils.format( project.mProject.getPublishedOn());

        if(project.mOwners!=null&&project.mOwners.size()!=0){
       username=project.mOwners.get(FIRST_OWNER_INDEX).getUsername();}
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
