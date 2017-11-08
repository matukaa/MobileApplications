package com.freakz.matukaa.mshare;

/**
 * Created by Matukaa on 2017-11-08.
 */

public class Song {
    public String title;
    public String artist;
    public String album;
    public String yt_link;

    public Song(String title, String artist, String album, String yt_link) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.yt_link = yt_link;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
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

    public void setYt_link(String yt_link) {
        this.yt_link = yt_link;
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

    public String getYt_link() {
        return yt_link;
    }
}
