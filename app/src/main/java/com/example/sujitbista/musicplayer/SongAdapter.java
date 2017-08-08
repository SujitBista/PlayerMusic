package com.example.sujitbista.musicplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sujitbista on 8/7/17.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private Context context;
    private ArrayList<SongInfo> songsList;
    public SongAdapter(Context context, ArrayList<SongInfo> songs){
        this.context = context;
        this.songsList = songs;
    }

    OnItemClickListener listener;

    public interface OnItemClickListener{
         void onItemClick(Button b, View v, SongInfo obj, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
         this.listener = onItemClickListener;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_songs, viewGroup,false);
        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(final SongHolder holder, final int position) {
         final SongInfo info = songsList.get(position);
         holder.songName.setText(info.songName);
         holder.artistName.setText(info.artistName);
         holder.btnaction.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(listener != null){
                     listener.onItemClick(holder.btnaction,view,info,position);
                 }
             }
         });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        TextView songName, artistName;
        Button btnaction;
        public SongHolder(View itemView) {
            super(itemView);
            songName = (TextView) itemView.findViewById(R.id.tvSongName);
            artistName = (TextView) itemView.findViewById(R.id.tvArtistName);
            btnaction = (Button) itemView.findViewById(R.id.btnAction);

        }
    }
}
