package com.freakz.matukaa.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.freakz.matukaa.lab4.Entities.Song;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrudActivity extends AppCompatActivity {

    private EditText crudTitleTB;
    private EditText crudArtistTB;
    private EditText crudAlbumTB;
    private Button crudDeleteButton;
    private Button crudEditButton;

    String id, title, artist, album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            onBackPressed();

        id = (String) bundle.get("id");
        title = (String) bundle.get("title");
        artist = (String) bundle.get("artist");
        album = (String) bundle.get("album");
        crudAlbumTB = findViewById(R.id.crudAlbumTB);
        crudArtistTB = findViewById(R.id.crudArtistTB);
        crudTitleTB = findViewById(R.id.crudTitleTB);
        crudDeleteButton = findViewById(R.id.crudDeleteButton);
        crudEditButton = findViewById(R.id.crudEditButton);

        crudAlbumTB.setText(album);
        crudTitleTB.setText(title);
        crudArtistTB.setText(artist);

        crudEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        String newTitle = crudTitleTB.getText().toString().trim();
                        String newAlbum = crudAlbumTB.getText().toString().trim();
                        String newArtist = crudArtistTB.getText().toString().trim();
                        updateSong(newTitle, newAlbum, newArtist, id);
                    }
                };
                new Thread(runnable).start();
                onBackPressed();
            }
        });

        crudDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        deleteSong(id);
                    }
                };
                new Thread(runnable).start();
                onBackPressed();
            }
        });
    }

    void updateSong(String newTitle, String newAlbum, String newArtist, String id) {
        DatabaseReference songRef = FirebaseDatabase.getInstance().getReference().child("Songs").child(id);
        songRef.child("title").setValue(newTitle);
        songRef.child("album").setValue(newAlbum);
        songRef.child("artist").setValue(newArtist);
    }

    void deleteSong(String id){
        FirebaseDatabase.getInstance().getReference().child("Songs").child(id).removeValue();
    }
}
