package com.example.mp3player;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPageAdaptor extends FragmentStatePagerAdapter {

    int tabCount;
    Context c;
    TabLayout tabLayout;

    public TabPageAdaptor(Context c, FragmentManager fm, int tabCount, TabLayout tab){
        super(fm);
        this.c=c;
        this.tabCount=tabCount;
        this.tabLayout=tab;

    }


    @SuppressLint("ResourceType")
    @Override
    public Fragment getItem(int i) {

        switch(i){
            case 0:
                 //tabLayout.setSelectedTabIndicator(0);
                 return new Songs();

            case 1:
                //tabLayout.setSelectedTabIndicator(1);
                return new Artist();
            case 2:
                //tabLayout.setSelectedTabIndicator(2);
                return new Album();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
