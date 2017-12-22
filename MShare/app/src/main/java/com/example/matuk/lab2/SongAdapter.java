package com.example.matuk.lab2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.matuk.lab2.db.Song;

import java.util.List;

/**
 * Created by matuk on 2017-12-22.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(@NonNull Context context, List<Song> songs) {
        super(context, R.layout.activity_view_list, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.activity_listview, parent, false);

        Song song = getItem(position);
        TextView title = customView.findViewById(R.id.titleTextView);
        TextView artist = customView.findViewById(R.id.artistTextView);
        TextView link = customView.findViewById(R.id.linkTextView);

        if (song != null) {
            title.setText(song.getTitle());
            artist.setText(song.getArtist());
            link.setText(song.getLink());
        }

        return customView;
    }
}

