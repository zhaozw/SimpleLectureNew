package com.simplelecture.main.adapters;

/**
 * Created by karthik.rao on 04-02-2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.simplelecture.main.fragments.ScreenSlidePageFragment;
import com.simplelecture.main.model.viewmodel.HomeBannersModel;

import java.util.List;

/**
 * A simple pager adapter that represents 5 {@link ScreenSlidePageFragment} objects, in
 * sequence.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;
    private final List<HomeBannersModel> bannersLstArray;

    public ScreenSlidePagerAdapter(FragmentManager fm, List<HomeBannersModel> bannersLstAray) {
        super(fm);

        bannersLstArray = bannersLstAray;
    }

    @Override
    public Fragment getItem(int position) {

        return ScreenSlidePageFragment.create(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
