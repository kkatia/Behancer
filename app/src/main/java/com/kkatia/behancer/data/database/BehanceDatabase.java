package com.kkatia.behancer.data.database;

import com.kkatia.behancer.data.model.project.Cover;
import com.kkatia.behancer.data.model.project.Owner;
import com.kkatia.behancer.data.model.project.Project;
import com.kkatia.behancer.data.model.user.Image;
import com.kkatia.behancer.data.model.user.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Vladislav Falzan.
 */

@Database(entities = {Project.class, Cover.class, Owner.class, User.class, Image.class}, version = 1)
public abstract class BehanceDatabase extends RoomDatabase {

    public abstract BehanceDao getBehanceDao();
}
