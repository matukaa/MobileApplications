package com.example.matuk.lab2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matuk on 2017-12-22.
 */

@Entity(tableName = "songs")
public class Song {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "artist")
    private String artist;
    @ColumnInfo(name = "link")
    private String link;
    @ColumnInfo(name = "date")
    private String publish_date;

    public Song(String title, String artist, String link, String publish_date) {
        this.title = title;
        this.artist = artist;
        this.link = link;
        this.publish_date = publish_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getLink() {
        return link;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    @Override
    public String toString() {
        return artist + ": " + title;
    }
}
