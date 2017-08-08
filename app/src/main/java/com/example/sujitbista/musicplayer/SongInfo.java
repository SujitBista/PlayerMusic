package com.example.sujitbista.musicplayer;

/**
 * Created by sujitbista on 8/7/17.
 */

public class SongInfo {
     public String songName,artistName,songUrl;

    public SongInfo(){

    }
    public SongInfo(String songName, String artistName, String songUrl) {
        this.songName = songName;
        this.artistName = artistName;
        this.songUrl = songUrl;
    }

    public String getSongUrl() {
        return songUrl;
    }
}
