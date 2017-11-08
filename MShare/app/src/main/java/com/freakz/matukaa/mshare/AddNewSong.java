package com.freakz.matukaa.mshare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddNewSong extends AppCompatActivity {

    EditText song_title_text;
    EditText song_artist_text;
    EditText song_album_text;
    EditText song_yt_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_song);

        song_title_text = findViewById(R.id.songTitleText);
        song_artist_text = findViewById(R.id.songArtistText);
        song_album_text = findViewById(R.id.songAlbumText);
        song_yt_text = findViewById(R.id.songYtText);
    }

    public boolean validate_text(EditText text){
        if (text.getText().toString().isEmpty())
        {
            text.setError("This is a required field.");
            return false;
        }

        return true;
    }

    public boolean validate_fields() {
        boolean is_valid = true;
        is_valid = validate_text(song_title_text) && is_valid;
        is_valid = validate_text(song_artist_text) && is_valid;
        is_valid = validate_text(song_yt_text) && is_valid;
        if (!Pattern.matches("https://youtu\\.be/.*", song_yt_text.getText().toString()) &&
            !Pattern.matches("https://www\\.youtube\\.com/.*", song_yt_text.getText().toString())){
            song_yt_text.setError("Must be a valid youtube link.");
            is_valid = false;
        }

        return is_valid;
    }

    public void submitForm(View view) {
        if (!validate_fields()){
            Toast.makeText(this, "Please resolve all errors.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent gmail = new Intent(Intent.ACTION_SEND);
        gmail.setData(Uri.parse("mailto:"));
        gmail.putExtra(Intent.EXTRA_EMAIL, new String[] {"mshare@gmail.com"});
        gmail.putExtra(Intent.EXTRA_SUBJECT, "New Song request");
        String email_text = "Title: " + song_title_text.getText().toString() + "\n" +
                "Artist: " + song_artist_text.getText().toString() + "\n" +
                "Album: " + song_album_text.getText().toString() + "\n" +
                "Youtube link: " + song_yt_text.getText().toString();

        gmail.putExtra(Intent.EXTRA_TEXT, email_text);
        gmail.setType("message/rfc822");
        Intent chooser = Intent.createChooser(gmail, "Send Email");
        startActivity(chooser);
    }
}
