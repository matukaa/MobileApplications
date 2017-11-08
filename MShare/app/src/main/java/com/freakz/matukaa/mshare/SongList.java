package com.freakz.matukaa.mshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class SongList extends AppCompatActivity {

    ListView song_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        song_list_view = findViewById(R.id.songListView);
        Song[] songs = { new Song("Title1", "Artist1", "Album1", "Link1"),
                        new Song("Title2", "Artist2", "Album2", "Link2")};

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("id")){
                int id = extras.getInt("id");
                songs[id].setTitle(extras.getString("title"));
                songs[id].setAlbum(extras.getString("album"));
                songs[id].setArtist(extras.getString("artist"));
                songs[id].setYt_link(extras.getString("ytlink"));
            }
        }
        ListAdapter adapter = new SongAdapter(this, songs);
        song_list_view.setAdapter(adapter);

        song_list_view.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Song song = (Song)adapterView.getItemAtPosition(i);
                        Intent editSong = new Intent(view.getContext(), EditSong.class);
                        editSong.putExtra("title", song.getTitle());
                        editSong.putExtra("artist", song.getArtist());
                        editSong.putExtra("album", song.getAlbum());
                        editSong.putExtra("ytlink", song.getYt_link());
                        editSong.putExtra("id", i);
                        startActivity(editSong);
                    }
                }
        );
    }
}
