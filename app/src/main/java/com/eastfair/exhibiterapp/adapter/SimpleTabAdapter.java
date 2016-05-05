package com.eastfair.exhibiterapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Chatikyan on 19.01.2016.
 */
public class SimpleTabAdapter extends FragmentPagerAdapter {

    private int page = 5;
    private String[] tabtitle = new String[]{"消息", "展商", "展品", "展场","我的"};

    public SimpleTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new Fragment();
    }

    @Override
    public int getCount() {
        return page;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitle[position];
    }
}

