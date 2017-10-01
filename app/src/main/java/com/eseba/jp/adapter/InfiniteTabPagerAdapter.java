package com.eseba.jp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eseba.jp.Config;
import com.eseba.jp.database.table.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielnguyen on 8/29/17.
 */
public class InfiniteTabPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = InfiniteTabPagerAdapter.class.getSimpleName();

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles;

    public InfiniteTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return this.getValueAt(position);
    }

    @Override
    public int getCount() {
        return Config.Tab.NUMBER_OF_LOOPS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (this.titles != null && this.titles.size() > 0) {
            return this.titles.get(position % this.titles.size());
        }
        return "";
    }

    public void addAll(List<Fragment> fragments, String allTabTitle, List<Genre> genreList) {
        this.fragments = new ArrayList<>(fragments);
        this.titles = new ArrayList<>();
        this.titles.add(allTabTitle);
        for (Genre genre : genreList) {
            this.titles.add(genre.getGenreName());
        }
    }

    public Fragment getValueAt(int position) {
        if (this.fragments.size() == 0) {
            return null;
        }
        return this.fragments.get(position % this.fragments.size());
    }
}