package com.example.mp3player;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;


public class Artist extends Fragment  {

    ArrayList<String> artistlist=new ArrayList<String>();
    ArrayList<String> list=new ArrayList<String>();
    GridView gv;
    GridAdapter adapter;
    MainActivity mA=new MainActivity();
    Songs songs=new Songs();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.artist_layout,container,false);
        gv=view.findViewById(R.id.gridView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        list=songs.getDistinctArtist();

        artistlist.add("Alan Walker");
        artistlist.add("Sia");
        artistlist.add("Seal Paul");
        artistlist.add("Arijit Singh");



        adapter=new GridAdapter(getActivity(),artistlist);
        gv.setAdapter(adapter);
       /*adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,  artistlist);
       gv.setAdapter(adapter);*/


    }

    /*public void getArtist(ArrayList<String> list){
            //artistlist=new ArrayList<String>(list);
        Log.d("list----->","run ---->Size<-------"+list.size());
    }*/


}
