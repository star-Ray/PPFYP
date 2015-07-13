package com.example.arnold.fypproject.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arnold.fypproject.Fragment.HomepageFragment;

/**
 * Created by Arnold on 7/13/2015.
 */
public class HomepageTabsPagerAdapter extends FragmentPagerAdapter {
    public HomepageTabsPagerAdapter (FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i){
        Fragment fragment = new HomepageFragment();
        Bundle args = new Bundle();
        args.putInt(HomepageFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount(){
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return "OBJECT" + (position + 1);
    }
}
