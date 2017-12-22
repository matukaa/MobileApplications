package com.example.matuk.lab2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.matuk.lab2.db.AppDatabase;
import com.example.matuk.lab2.db.Song;
import com.example.matuk.lab2.db.SongDao;

import java.util.Calendar;

/**
 * Created by matuk on 2017-12-22.
 */

public class AddSongActivity extends AppCompatActivity {

    final Context context=this;

    EditText titleEditText, artistEditText, linkEditText, dateEditText;
    SongDao songDao;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        titleEditText=findViewById(R.id.EditTextTitle);
        linkEditText=findViewById(R.id.EditTextLink);
        artistEditText=findViewById(R.id.EditTextArtist);
        dateEditText=findViewById(R.id.EditTextDate);

        addButton = (Button) findViewById(R.id.buttonAddSong);

        songDao = AppDatabase.getAppDatabase(this).songDao();

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                addSong();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Delete");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Song added!")
                        .setCancelable(false)
                ;

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        setUpPicker();
    }

    public void setUpPicker() {
        final Calendar myCalendar = Calendar.getInstance();

        EditText edittext = (EditText) findViewById(R.id.EditTextDate);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String txt = "" + year + "/" + monthOfYear + "/" + dayOfMonth;
                dateEditText.setText(txt);
            }
        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddSongActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void addSong() {
        String title = titleEditText.getText().toString();
        String artist = artistEditText.getText().toString();
        String link = linkEditText.getText().toString();
        String date = dateEditText.getText().toString();
        Song song = new Song(title, artist, link, date);

        songDao.insertSongs(song);
        onBackPressed();

    }
}
