package com.freakz.matukaa.lab4;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.freakz.matukaa.lab4.Entities.Song;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddSongActivity extends AppCompatActivity {

    EditText titleEditText, artistEditText, albumEditText, linkEditText, dateEditText;
    Button addButton;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        id = getIntent().getExtras().getString("id");
        titleEditText=findViewById(R.id.addTitleTB);
        linkEditText=findViewById(R.id.addLinkTB);
        artistEditText=findViewById(R.id.addArtistTB);
        dateEditText=findViewById(R.id.addDateTB);
        albumEditText=findViewById(R.id.addAlbumTB);

        addButton = findViewById(R.id.addSongButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        String title = titleEditText.getText().toString().trim();
                        String artist = artistEditText.getText().toString().trim();
                        String album = albumEditText.getText().toString().trim();
                        String link = linkEditText.getText().toString().trim();
                        String date = dateEditText.getText().toString().trim();
                        addSong(title, artist, album, link, date, id);
                    }
                };
                new Thread(runnable).start();
                onBackPressed();
            }
        });
        setupPicker();
    }

    void addSong(String title, String artist, String album, String link, String date, String id){
        Song song = new Song(title, artist, album, link, date);
        song.setId(id);
        song.writeToDb(FirebaseDatabase.getInstance().getReference());
    }

    void setupPicker(){
        final Calendar myCalendar = Calendar.getInstance();

        EditText edittext = findViewById(R.id.addDateTB);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String txt = "" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                dateEditText.setText(txt);
            }
        };

        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddSongActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
}
