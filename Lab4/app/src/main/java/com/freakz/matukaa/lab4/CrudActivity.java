package com.freakz.matukaa.lab4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.freakz.matukaa.lab4.Entities.Song;
import com.freakz.matukaa.lab4.Manager.AppManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CrudActivity extends AppCompatActivity {

    final Context context = this;

    private EditText crudTitleTB;
    private EditText crudArtistTB;
    private EditText crudAlbumTB;
    private Button crudDeleteButton;
    private Button crudEditButton;
    PieChart chart;

    String id;
    Song currentSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            onBackPressed();

        id = (String) bundle.get("id");
        for (Song s : AppManager.getInstance().songList)
            if (s.getId().equals(id))
                currentSong = s;

        crudAlbumTB = findViewById(R.id.crudAlbumTB);
        crudArtistTB = findViewById(R.id.crudArtistTB);
        crudTitleTB = findViewById(R.id.crudTitleTB);
        crudDeleteButton = findViewById(R.id.crudDeleteButton);
        crudEditButton = findViewById(R.id.crudEditButton);

        crudAlbumTB.setText(currentSong.getAlbum());
        crudTitleTB.setText(currentSong.getTitle());
        crudArtistTB.setText(currentSong.getArtist());
        chart = findViewById(R.id.PieChart);
        setupChart();

        crudEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Edit");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to modify the data?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int clickId) {
                                String title = crudTitleTB.getText().toString().trim();
                                String album = crudAlbumTB.getText().toString().trim();
                                String artist = crudArtistTB.getText().toString().trim();
                                AppManager.getInstance().updateSong(id, title, artist, album);
                                onBackPressed();
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

        crudDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Delete");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int clickId) {
                                AppManager.getInstance().deleteSong(id);
                                onBackPressed();
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

    private void setupChart()
    {
        chart.setRotationEnabled(true);
        chart.setHoleRadius(10.f);
        chart.setTransparentCircleAlpha(0);
        chart.setDrawEntryLabels(false);
        Description description = new Description();
        description.setText("The artist's market share");
        chart.setDescription(description);

        List<Song> allSongs = AppManager.getInstance().songList;
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        int currentArtists = 0;
        for (int i = 0; i < allSongs.size(); ++i)
        {
            String artist = allSongs.get(i).getArtist();
            if (artist.equals(currentSong.getArtist()))
                currentArtists++;

            xEntries.add(artist);
        }

        float pct1 = (float)currentArtists / (float)xEntries.size() * 100.f;
        PieEntry entry1 = new PieEntry(pct1, 0);
        PieEntry entry2 = new PieEntry(100.f - pct1, 1);
        yEntries.add(entry1); yEntries.add(entry2);

        xEntries.add(currentSong.getArtist());
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
}
