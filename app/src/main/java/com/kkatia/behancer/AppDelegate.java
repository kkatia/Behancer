package com.kkatia.behancer;

import android.app.Application;

import com.kkatia.behancer.data.Storage;
import com.kkatia.behancer.data.database.BehanceDatabase;

import androidx.room.Room;

public class AppDelegate  extends Application {

    private Storage mStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        final BehanceDatabase database = Room.databaseBuilder(this, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();

        mStorage = new Storage(database.getBehanceDao());
    }

    public Storage getStorage() {
        return mStorage;
    }
}
