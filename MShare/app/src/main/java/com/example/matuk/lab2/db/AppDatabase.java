package com.example.matuk.lab2.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by matuk on 2017-12-22.
 */

@Database(version = 2, entities = {Song.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    abstract public SongDao songDao();

    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,"song-database"
            )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
