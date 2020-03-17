package com.kkatia.behancer.ui.projects;

import com.kkatia.behancer.common.BaseView;
import com.kkatia.behancer.data.model.project.Project;

import java.util.List;

public interface ProjectsView extends BaseView {
    void showProjects(List<Project> projects);
    void openProfileFragment(String username);

    void showRefresh();

    void hideRefresh();
}
