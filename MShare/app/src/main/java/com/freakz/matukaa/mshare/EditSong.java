package com.freakz.matukaa.mshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class EditSong extends AppCompatActivity {

    TextView title_text_view;
    TextView artist_text_view;
    TextView album_text_view;
    TextView yt_text_view;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        title_text_view = findViewById(R.id.titleText);
        artist_text_view = findViewById(R.id.artistText);
        album_text_view = findViewById(R.id.albumText);
        yt_text_view = findViewById(R.id.ytText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title_text_view.setText(extras.getString("title"));
            artist_text_view.setText(extras.getString("artist"));
            album_text_view.setText(extras.getString("album"));
            yt_text_view.setText(extras.getString("ytlink"));
            id = extras.getInt("id");
        }
    }

    public boolean validate_text(TextView text){
        if (text.getText().toString().isEmpty())
        {
            text.setError("This is a required field.");
            return false;
        }

        return true;
    }

    public boolean validate_fields() {
        boolean is_valid = true;
        is_valid = validate_text(title_text_view) && is_valid;
        is_valid = validate_text(artist_text_view) && is_valid;
        is_valid = validate_text(yt_text_view) && is_valid;
        if (!Pattern.matches("https://youtu\\.be/.*", yt_text_view.getText().toString()) &&
                !Pattern.matches("https://www\\.youtube\\.com/.*", yt_text_view.getText().toString())){
            yt_text_view.setError("Must be a valid youtube link.");
            is_valid = false;
        }

        return is_valid;
    }

    public void editSong(View view){
        if (!validate_fields()){
            Toast.makeText(this, "Please resolve all errors.", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = title_text_view.getText().toString();
        String artist = artist_text_view.getText().toString();
        String album = album_text_view.getText().toString();
        String yt_link = yt_text_view.getText().toString();

        Intent showList = new Intent(this, SongList.class);
        showList.putExtra("title", title);
        showList.putExtra("album", album);
        showList.putExtra("artist", artist);
        showList.putExtra("ytlink", yt_link);
        showList.putExtra("id", id);
        startActivity(showList);
    }
}
