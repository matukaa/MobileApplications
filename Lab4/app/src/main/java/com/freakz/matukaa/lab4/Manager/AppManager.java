package com.freakz.matukaa.lab4.Manager;

import com.freakz.matukaa.lab4.Entities.Song;
import com.freakz.matukaa.lab4.Entities.SongAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matuk on 2018-01-14.
 */

public class AppManager {

    private static AppManager instance;
    public List<Song> songList = new ArrayList<>();
    public SongAdapter songAdapter;

    private AppManager(){}

    public static synchronized AppManager getInstance(){
        if(instance == null){
            instance = new AppManager();
        }
        return instance;
    }

    public void notifyListChanged(){
        songAdapter.notifyDataSetChanged();
    }

    public void updateSong(String id, String title, String artist, String album){
        DatabaseReference songRef = FirebaseDatabase.getInstance().getReference().child("Songs").child(id);
        songRef.child("title").setValue(title);
        songRef.child("album").setValue(album);
        songRef.child("artist").setValue(artist);
        notifyListChanged();
    }

    public void deleteSong(String id){
        FirebaseDatabase.getInstance().getReference().child("Songs").child(id).removeValue();
        notifyListChanged();
    }

    public void addSong(String title, String artist, String album, String link, String date, String id){
        Song song = new Song(title, artist, album, link, date);
        song.setId(id);
        song.writeToDb(FirebaseDatabase.getInstance().getReference());
    }

    public void filterSongs(String criteria){
        songAdapter.getFilter().filter(criteria);
    }
}
