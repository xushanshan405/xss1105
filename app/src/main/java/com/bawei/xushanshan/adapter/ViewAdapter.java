package com.bawei.xushanshan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments;
    FragmentManager fm;
    public ViewAdapter(ArrayList<Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
