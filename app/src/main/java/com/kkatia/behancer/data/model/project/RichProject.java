package com.kkatia.behancer.data.model.project;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class RichProject {
    @Embedded
    private Project mProject;

    @Relation(entity = Owner.class, entityColumn = "project_id",parentColumn = "id")
    private List<Owner> mOwners;

    public void setProject(Project mProject) {
        this.mProject = mProject;
    }

    public void setOwners(List<Owner> owners) {
        this.mOwners = owners;
    }
}