package com.asneiya.neobyte.umkmdepok.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.asneiya.neobyte.umkmdepok.ui.TabPager.TabHomeFeed;
import com.asneiya.neobyte.umkmdepok.ui.TabPager.TabHotUkm;

/**
 * Created by neobyte on 8/24/2016.
 */
public class TabAdapter  extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TabHomeFeed();
            case 1:
                return new TabHotUkm();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}