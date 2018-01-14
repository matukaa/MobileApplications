package com.freakz.matukaa.lab4.Manager;

import com.freakz.matukaa.lab4.Entities.Song;
import com.freakz.matukaa.lab4.Entities.SongAdapter;

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

}
