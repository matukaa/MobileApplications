package com.freakz.matukaa.lab4.Entities;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Matukaa on 2018-01-14.
 */

@IgnoreExtraProperties
public class Song {
    public String id;
    public String title;
    public String artist;
    public String album;
    public String link;
    public String publish_date;

    public Song(){

    }

    public Song(String title, String artist, String album, String link, String publish_date) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.link = link;
        this.publish_date = publish_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", link='" + link + '\'' +
                ", publish_date='" + publish_date + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getLink() {
        return link;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void writeToDb(DatabaseReference databaseReference){
        DatabaseReference childRef = databaseReference.child("Songs").child(id);
        childRef.child("title").setValue(title);
        childRef.child("artist").setValue(artist);
        childRef.child("album").setValue(album);
        childRef.child("link").setValue(link);
        childRef.child("date").setValue(publish_date);
    }
}
