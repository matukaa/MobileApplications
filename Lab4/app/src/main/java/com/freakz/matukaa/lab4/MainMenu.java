package com.freakz.matukaa.lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.freakz.matukaa.lab4.Entities.Song;
import com.freakz.matukaa.lab4.Entities.SongAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    DatabaseReference databaseReference;
    SongAdapter songAdapter;
    List<Song> songList = new ArrayList<>();
    ListView songListView;
    boolean fill = false;
    private Button addButton;

    static boolean isUserAdmin(FirebaseUser user){
        return user != null && user.getEmail() != null && user.getEmail().equals(MainActivity.ADMIN_EMAIL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        addButton = findViewById(R.id.addButton);
        if (!isUserAdmin(FirebaseAuth.getInstance().getCurrentUser()))
            addButton.setVisibility(View.GONE);
        else
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getBaseContext(), AddSongActivity.class);
                    int largestId = 0;
                    for (Song s : songList) {
                        int songId = Integer.parseInt(s.getId());
                        if (songId > largestId)
                            largestId = songId;
                    }

                    largestId++;
                    i.putExtra("id", "" + largestId);
                    startActivity(i);
                }
            });
        songListView = findViewById(R.id.songListView);
        songAdapter = new SongAdapter(this, songList);
        songListView.setAdapter(songAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Songs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                songList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Song song = ds.getValue(Song.class);
                    if (song != null) {
                        song.setId(ds.getKey());
                        //Log.d("Song", song.toString());
                        songList.add(song);
                    }
                }
                songAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        songListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d("User", user.getEmail());
                if (!isUserAdmin(FirebaseAuth.getInstance().getCurrentUser()))
                    return;

                Intent crud = new Intent(getBaseContext(), CrudActivity.class);
                Song song = songList.get(i);
                crud.putExtra("id", song.getId());
                crud.putExtra("title", song.getTitle());
                crud.putExtra("artist", song.getArtist());
                crud.putExtra("album", song.getAlbum());
                startActivity(crud);
            }
        });

        if (fill) {
            Song s1 = new Song("Title1", "Artist1", "Album1", "Link1", "2017/02/03");
            Song s2 = new Song("Title2", "Artist2", "Album2", "Link2", "2017/02/03");
            s1.setId("1");
            s2.setId("2");
            s1.writeToDb(databaseReference);
            s2.writeToDb(databaseReference);
        }
    }
}
