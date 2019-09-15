package com.example.mp3player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseAdapter extends ArrayAdapter {

     ArrayList<GetSet> model;
     Context c;

    public BaseAdapter(Context c, ArrayList<GetSet> model) {
        super(c,R.layout.row,model);
        this.c=c;
        this.model=model;
    }


    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater lf= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=lf.inflate(R.layout.row,parent,false);

       TextView songName=row.findViewById(R.id.songName);
       TextView artist=row.findViewById(R.id.artistName);
       TextView duration=row.findViewById(R.id.duration);
        ImageView image= row.findViewById(R.id.image);

        MediaMetadataRetriever mdr=new MediaMetadataRetriever();
        mdr.setDataSource(model.get(position).getSongURL());
        byte[] art=mdr.getEmbeddedPicture();
        if(art!=null)
        {
            //     InputStream is = new ByteArrayInputStream(mmr.getEmbeddedPicture());
            Bitmap bm = BitmapFactory.decodeByteArray(art, 0, art.length);
            image.setImageBitmap(bm);
        }
        else
        {
            //image.setImageDrawable(getResources().getDrawable(R.drawable.audio));
        }

        songName.setText(model.get(position).getSongName());
        artist.setText(model.get(position).getArtistName());
        duration.setText(model.get(position).getDuration());

        return row;
    }
}
