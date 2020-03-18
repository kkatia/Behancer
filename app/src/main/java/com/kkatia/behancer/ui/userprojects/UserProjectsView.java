package com.kkatia.behancer.ui.userprojects;

import com.kkatia.behancer.common.BaseView;
import com.kkatia.behancer.data.model.project.Project;

import java.util.List;

public interface UserProjectsView  extends BaseView {
    void showProjects(List<Project> projects);
    void showRefresh();
    void hideRefresh();
}
