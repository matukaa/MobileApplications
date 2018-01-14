package com.freakz.matukaa.lab4.Entities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.freakz.matukaa.lab4.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matukaa on 2018-01-14.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    List<Song> songList = new ArrayList<>();
    List<Song> originalList = new ArrayList<>();

    public SongAdapter(@NonNull Context context, List<Song> songs) {
        super(context, R.layout.item_list_view, songs);
        songList = songs;
        originalList = songs;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.item_list_view, parent, false);

        Song song = songList.get(position);
        TextView title = customView.findViewById(R.id.titleTextView);
        TextView artist = customView.findViewById(R.id.artistTextView);
        TextView album = customView.findViewById(R.id.albumTextView);

        title.setText(song.getTitle());
        artist.setText(song.getArtist());
        album.setText(song.getAlbum());

        return customView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults res = new FilterResults();
                if (constraint == null || constraint.length() == 0){
                    res.values = originalList;
                    res.count = originalList.size();
                }
                else{
                    ArrayList<Song> filteredSongs = new ArrayList<>();
                    for (Song song : originalList)
                        if (song.getTitle().toLowerCase().contains(constraint) ||
                                song.getArtist().toLowerCase().contains(constraint))
                            filteredSongs.add(song);
                    res.values = filteredSongs;
                    res.count = filteredSongs.size();
                }
                return res;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                songList = (ArrayList<Song>)filterResults.values;
                if (songList != null && songList.size() > 0)
                    notifyDataSetChanged();
                else
                    notifyDataSetInvalidated();
            }
        };
    }
}