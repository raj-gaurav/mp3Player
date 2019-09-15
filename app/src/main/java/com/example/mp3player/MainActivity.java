package com.example.mp3player;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    SeekBar seekBar;
    Toolbar toolbar;
    ArrayList<String> li=new ArrayList<String>();
    MediaPlayer mediaPlayer = new MediaPlayer();;
    Handler handler=new Handler();
    TabLayout tab;
    ViewPager pager;
    TabPageAdaptor pageAdaptor;
    ImageView imgp;
    String songName;
    public TextView namep1,current,total;
    //Artist ar=new Artist();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgp=findViewById(R.id.imgplay);
        namep1=(TextView) findViewById(R.id.nameplay);
        current=(TextView) findViewById(R.id.tvcurrentplay);
        total=(TextView) findViewById(R.id.tvtotalplay);
        seekBar=findViewById(R.id.seekBarplay);
       //



        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("mp3Player");

        tab=findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Songs"));
        tab.addTab(tab.newTab().setText("Artists"));
        tab.addTab(tab.newTab().setText("Albums"));

        pager=findViewById(R.id.pager);
        pageAdaptor=new TabPageAdaptor(getApplicationContext(),getSupportFragmentManager(),tab.getTabCount(),tab);
        pager.setAdapter(pageAdaptor);
        pager.setOffscreenPageLimit(pageAdaptor.getCount()>1?pageAdaptor.getCount()-1:1);


        tab.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tab.setScrollPosition(i,0,true);
                pager.setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });









    }

    public void playmusic(final ArrayList<GetSet> songs, final int position){
        //start music
        //if(position<size) {
        Runnable r=new Runnable() {
            @Override
            public void run() {

                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    mediaPlayer=null;
                }
                try {

                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(songs.get(position).getSongURL());

                    mediaPlayer.prepare();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {


                            //seekBar.setProgress(0);
                            //seekBar.setMax(mediaPlayer.getDuration());
                            mp.start();
                            songName=songs.get(position).getSongName();
                            Log.d("Progress","song playing ------------->"+songName);
                            //Toast.makeText(getApplicationContext(),"music"+songName,Toast.LENGTH_SHORT).show();
                            //namep1.setText(songName);
                            //MainActivity.this.namep.setText("ghccghc");
                            // mp.setLooping(true);
                            //Log.d("Prog", "run: " + mediaPlayer.getDuration());
                        }
                    });

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            int p=position+1;
                            try {
                                if(p<songs.size())
                                {
                                    playmusic(songs,p);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    });

                } catch (IOException e) {
                }

            }
        };

        handler.postDelayed(r,100);
        Log.d("Prog", "run: " + "Helloooooooooooooo");

        Thread t=new MyThread();
        t.start();


    }

    public class MyThread extends Thread{


        @Override
        public void run() {
            while(true){
                Thread thread=new Thread();
                try {
                    thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mediaPlayer != null) {
                    /*seekBar.post(new Runnable() {
                        @Override
                        public void run() {

                            //seekBar.setProgress(mediaPlayer.getCurrentPosition());
                            //
                        }
                    });*/
                    Log.d("seekBar","progress-------->curent time-------->"+mediaPlayer.getCurrentPosition());

                }
                else{

                }

            }


        }
    }

   public ArrayList<String> artist(ArrayList<String> list){

       Log.d("song to artist list","---------->list size<-----------------"+list.size());
       li=list;

       //ar.getArtist(list);
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  namep1.setText(songName);
    }
}
