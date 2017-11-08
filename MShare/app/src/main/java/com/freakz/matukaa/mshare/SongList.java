package com.freakz.matukaa.mshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SongList extends AppCompatActivity {

    ListView song_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        song_list_view = findViewById(R.id.songListView);
        Song[] songs = { new Song("Title1", "Artist1", "Album1", "Link1"),
                        new Song("Title2", "Artist2", "Album2", "Link2")};

        ListAdapter adapter = new SongAdapter(this, songs);
        song_list_view.setAdapter(adapter);
    }
}
