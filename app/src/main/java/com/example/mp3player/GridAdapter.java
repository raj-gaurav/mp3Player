package com.example.mp3player;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class GridAdapter extends ArrayAdapter {


    ArrayList<String> model;
   // ArrayList<String> artist=new ArrayList<String>();
    Context c;

    public GridAdapter(Context c, ArrayList<String> model) {
        super(c,R.layout.grid_cell,model);
        this.c=c;
        this.model=model;
    }


    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater lf= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cell=lf.inflate(R.layout.grid_cell,parent,false);


        TextView ar=cell.findViewById(R.id.artista);
        ImageView img=cell.findViewById(R.id.imga);




        ar.setText( model.get(position));
       // Log.d("Artist","Run : --------------------->"+model.get(position));
        return cell;
    }
}
