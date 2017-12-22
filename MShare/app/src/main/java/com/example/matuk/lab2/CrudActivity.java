package com.example.matuk.lab2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.matuk.lab2.db.AppDatabase;
import com.example.matuk.lab2.db.Song;
import com.example.matuk.lab2.db.SongDao;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matuk on 2017-12-22.
 */

public class CrudActivity extends AppCompatActivity {

    final Context context=this;

    EditText titleEditText, artistEditText, linkEditText;
    SongDao songDao;
    Song selectedSong;
    Button deleteButton, updateButton;
    PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        titleEditText=(EditText)findViewById(R.id.EditTextTitle);
        artistEditText=(EditText)findViewById(R.id.EditTextArtist);
        linkEditText=findViewById(R.id.EditTextLink);
        chart = findViewById(R.id.PieChart);

        songDao= AppDatabase.getAppDatabase(this).songDao();

        selectedSong = songDao.getSong(getIntent().getIntExtra("id", 0));

        titleEditText.setText(selectedSong.getTitle());
        artistEditText.setText(selectedSong.getArtist());
        linkEditText.setText(selectedSong.getLink());

        deleteButton = (Button) findViewById(R.id.buttonDeleteSong);
        updateButton = (Button) findViewById(R.id.buttonUpdateSong);

        // add button listener
        setButtonListeners();
        setupChart();
    }

    private void setupChart()
    {
        chart.setRotationEnabled(true);
        chart.setHoleRadius(10.f);
        chart.setTransparentCircleAlpha(0);
        chart.setDrawEntryLabels(false);
        Description description = new Description();
        description.setText("The artist's market share");
        chart.setDescription(description);

        List<Song> allSongs = songDao.getAll();
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        int currentArtists = 0;
        for (int i = 0; i < allSongs.size(); ++i)
        {
            String artist = allSongs.get(i).getArtist();
            if (artist.equals(selectedSong.getArtist()))
                currentArtists++;

            xEntries.add(artist);
        }

        float pct1 = (float)currentArtists / (float)xEntries.size() * 100.f;
        PieEntry entry1 = new PieEntry(pct1, 0);
        PieEntry entry2 = new PieEntry(100.f - pct1, 1);
        yEntries.add(entry1); yEntries.add(entry2);

        xEntries.add(selectedSong.getArtist());
        xEntries.add("Other artists");

        PieDataSet pieDataSet = new PieDataSet(yEntries, "Artists");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        chart.setData(pieData);
        chart.invalidate();
    }

    private void selectSong(Song song) {
        selectedSong = song;
        titleEditText.setText(selectedSong.getTitle());
        artistEditText.setText(selectedSong.getArtist());
        linkEditText.setText(selectedSong.getLink());
    }

    public void listSongs(View view) {
        Intent intent = new Intent(this, ViewListActivity.class);

        startActivity(intent);
    }

    public void deleteSong() {
        songDao.deleteSong(selectedSong);
        onBackPressed();
    }
    public void updateSong() {
        String title=titleEditText.getText().toString();
        String artist=artistEditText.getText().toString();
        selectedSong.setTitle(title);
        selectedSong.setArtist(artist);
        selectedSong.setLink(linkEditText.getText().toString());

        songDao.updateSong(selectedSong);

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, ViewListActivity.class);
        startActivity(setIntent);
        finish();
    }

    private void setButtonListeners(){
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Delete");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                deleteSong();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Update");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to update?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                updateSong();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

}
