package com.kkatia.behancer.data.database;

import com.kkatia.behancer.data.model.project.Cover;
import com.kkatia.behancer.data.model.project.Owner;
import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.data.model.project.RichProject;
import com.kkatia.behancer.data.model.user.Image;
import com.kkatia.behancer.data.model.user.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Vladislav Falzan.
 */
@Dao
public interface BehanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProjects(List<Project> projects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCovers(List<Cover> covers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOwners(List<Owner> owners);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(Image image);

    @Query("select * from project order by published_on")
    LiveData<List<RichProject>> getProjectsLive();

    @Query("select * from project order by published_on")
    DataSource.Factory<Integer,RichProject> getProjectsPaged();
//м DataSource.Factory <Integer, RichProject>

    @Query("select * from project")
    List<Project> getProjects();

    @Query("select * from owner where project_id = :projectId")
    List<Owner> getOwnersFromProject(int projectId);

    @Query("select * from user where username = :userName")
    User getUserByName(String userName);

    @Query("select * from image where user_id = :userId")
    Image getImageFromUser(int userId);

    @Query("delete from owner")
    void clearOwnerTable();

    @Query("delete from cover")
    void clearCoverTable();

    @Query("delete from image")
    void clearImageTable();

    @Query("select * from user")
    List<User> getUsers();

    @Query("select * from image")
    List<Image> getImages();
}
