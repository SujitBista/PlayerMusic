package com.example.sujitbista.musicplayer;

import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//public class MainActivity extends AppCompatActivity {
public class MainActivity extends AppCompatActivity {
//    private static final String SD_PATH = new String("/sdcard/");
//    private List<String> songs = new ArrayList<String>();
//    private MediaPlayer mp = new MediaPlayer();

      private ArrayList<SongInfo> songs = new ArrayList<SongInfo>();
      RecyclerView recyclerView;
      SongAdapter songAdapter;
      MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        SongInfo s = new SongInfo("Cheap Thrills","Sia","http://www.ceetlehero.com/songs/Siacomeon.mp3");
        songs.add(s);
        songAdapter = new SongAdapter(this,songs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(songAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        songAdapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Button b, View v, SongInfo obj, int position) {
                try {
                    if(b.getText().toString().equals("Stop")){
                        b.setText("Play");
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }else {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(obj.getSongUrl());
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                                b.setText("Stop");
                            }
                        });
                    }

                }catch (IOException ex){

                }
            }

        });
    }
}
