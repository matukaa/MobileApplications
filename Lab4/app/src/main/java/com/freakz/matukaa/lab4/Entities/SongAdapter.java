package com.freakz.matukaa.lab4.Entities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.freakz.matukaa.lab4.R;

import java.util.List;

/**
 * Created by Matukaa on 2018-01-14.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(@NonNull Context context, List<Song> songs) {
        super(context, R.layout.item_list_view, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.item_list_view, parent, false);

        Song song = getItem(position);
        TextView title = customView.findViewById(R.id.titleTextView);
        TextView artist = customView.findViewById(R.id.artistTextView);
        TextView album = customView.findViewById(R.id.albumTextView);

        title.setText(song.getTitle());
        artist.setText(song.getArtist());
        album.setText(song.getAlbum());

        return customView;
    }
}