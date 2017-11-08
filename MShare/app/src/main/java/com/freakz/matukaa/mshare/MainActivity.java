package com.freakz.matukaa.mshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAddNewSongForm(View view) {
        Intent addSongActivity = new Intent(this, AddNewSong.class);
        startActivity(addSongActivity);
    }

    public void openSongList(View view){
        Intent openSongsActivity = new Intent(this, SongList.class);
        startActivity(openSongsActivity);
    }
}
