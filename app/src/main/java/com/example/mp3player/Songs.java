package com.example.mp3player;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Songs extends Fragment {

    public ArrayList<GetSet> songs= new ArrayList<GetSet>();
    ListView lv;
    BaseAdapter adapter;

    TreeSet<String> artists=new TreeSet<String>();
    ArrayList<String> artistlist=new ArrayList<String>();
    ArrayList<String> list=new ArrayList<String>();
    MainActivity mA=new MainActivity();





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_layout,container,false);
        lv=view.findViewById(R.id.lv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkPermissions();
        list=getDistinctArtist();
        mA.artist(list);
        //songs.add(new GetSet("Faded","Alan Walker","ueueueueueu","102020"));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Snackbar.make(view,"Song :"+songs.get(position).getSongName(),Snackbar.LENGTH_SHORT).show();
                mA.playmusic(songs,position);
                Log.d("Prog", "run: " + "Hiiiiiiiiiii");
            }
        });

    }

    private void checkPermissions(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
                return;
            }
        }
        showSongs();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    showSongs();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    checkPermissions();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }

    }


    private void showSongs(){

        Uri musicUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor c1 = getActivity().getApplicationContext().getContentResolver().query(musicUri, null, MediaStore.Audio.Media.IS_MUSIC+"!=0", null, null);
        c1.moveToFirst();
        while (c1.isAfterLast() == false) {
            //String title = c1.getString(c1.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String artist = c1.getString(c1.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String url = c1.getString(c1.getColumnIndex(MediaStore.Audio.Media.DATA));
            String song = c1.getString(c1.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            String duration=c1.getString(c1.getColumnIndex(MediaStore.Audio.Media.DURATION));
            // String image =c1.getString(c1.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART));

            //float c=Float.parseFloat(duration)/(1000*60);



            String dur=timeConverter(String.valueOf(duration));
            songs.add(new GetSet(song,artist,url,dur));
            c1.moveToNext();
        }

        c1.close();
        adapter=new BaseAdapter(getActivity(),songs);
        lv.setAdapter(adapter);

    }

    public String timeConverter(String time){
        int hrs=Integer.parseInt(time)/(1000*60*60);
        int min=(Integer.parseInt(time)%(1000*60*60))/(1000*60);
        int sec=((Integer.parseInt(time)%(1000*60*60))%(1000*60))/1000;
        String dur,minute,second;

        if(min<10)
            minute="0"+String.valueOf(min);
        else
            minute=String.valueOf(min);

        if(sec<10)
            second="0"+String.valueOf(sec);
        else
            second=String.valueOf(sec);

        if(hrs>0)
        {
            dur = String.valueOf(hrs)+":"+minute+":"+second ;
        }
        else
            dur=minute+":"+second;


        return dur;
    }



    public ArrayList<String> getDistinctArtist(){


        for(int i=0;i<songs.size();i++){
            String str=songs.get(i).getArtistName();
            if(artists.add(str)){
                artistlist.add(str);

            }
        }



        return artistlist;
    }
}
