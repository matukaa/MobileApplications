package com.example.matuk.lab2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by matuk on 2017-12-22.
 */

@Dao
public interface SongDao {

    @Query("SELECT * FROM songs")
    List<Song> getAll();

    @Query("SELECT * FROM songs WHERE id = :id LIMIT 1")
    Song getSong(int id);

    @Insert
    void insertSongs(Song... songs);

    @Delete
    public void deleteSong(Song... song);

    @Update
    public void updateSong(Song... song);

    @Query("DELETE FROM songs")
    void deleteAll();
}