package com.example.arnold.fypproject.Activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.arnold.fypproject.Fragment.HomepageFragment;
import com.example.arnold.fypproject.R;

public class Test3Activity extends ActionBarActivity implements ActionBar.TabListener{

    HomepageTabsPagerAdapter homepageTabsPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        viewPager = (ViewPager)findViewById(R.id.pager_test);
        homepageTabsPagerAdapter = new HomepageTabsPagerAdapter(getSupportFragmentManager());

//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setDisplayHomeAsUpEnabled(false);
        viewPager.setAdapter(homepageTabsPagerAdapter);

////        Adding Tabs
//        for (String tab_name : tabs){
//            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
//        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class HomepageTabsPagerAdapter extends FragmentPagerAdapter {
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
}
