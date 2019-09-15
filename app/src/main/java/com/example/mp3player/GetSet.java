package com.example.mp3player;

public class GetSet {

    String songName="",artistName="", songURL="", duration="";
    //Bitmap image;
    public GetSet(String songName, String artistName, String songURL, String duration){
        this.songName=songName;
        this.artistName=artistName;
        this.songURL=songURL;
        this.duration=duration;

    }



    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getSongURL() {
        return songURL;
    }

    public String getDuration() {
        return duration;
    }
}
