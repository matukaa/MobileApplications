package com.example.matuk.lab2.db;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * Created by matuk on 2017-12-22.
 */

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static Song addSong(final AppDatabase db, Song song){
        db.songDao().insertSongs(song);
        return song;
    }

    private static void populateWithTestData(AppDatabase db) {
        Song song = new Song("Test1", "Artist1", "Link1", "unk_date");
        addSong(db, song);

        List<Song> songList = db.songDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count: " + songList.size());
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
