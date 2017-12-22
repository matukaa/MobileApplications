package com.example.matuk.lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.matuk.lab2.db.AppDatabase;
import com.example.matuk.lab2.db.Song;
import com.example.matuk.lab2.db.SongDao;

import java.util.List;

/**
 * Created by matuk on 2017-12-22.
 */

public class ViewListActivity extends Activity {
    // Array of strings...
    /*String[] mobileArray = {"Don’t regret the past, just learn from it.",
                            "Life is trying things to see if they work.",
                            "It does not matter how slowly you go as long as you do not stop.",
                            "Wherever you go, go with all your heart.",
                            "Everything you can imagine is real.",
                            "Don’t wait. The time will never be just right.",
                            "Men are born to succeed, not fail.",
                            "Whatever you are, be a good one."};*/
    List<Song> songList;
    SongDao songDao;
    ListView listView;

    Song selectedSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        songDao = AppDatabase.getAppDatabase(this).songDao();

        //DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));
        songList = songDao.getAll();

        SongAdapter adapter = new SongAdapter(this, songList);

        listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView. setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                selectedSong = songList.get(position);

                Intent intent = new Intent(ViewListActivity.this, CrudActivity.class);

                intent.putExtra("id", selectedSong.getId());
                startActivity(intent);
                finish();
            }
        });

    }
}
