package com.example.arnold.fypproject.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arnold.fypproject.R;

public class Test3Activity extends FragmentActivity {

    TestCollectionPagerAdapter  testCollectionPagerAdapter;;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        testCollectionPagerAdapter = new TestCollectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.pager_test);
        viewPager.setAdapter(testCollectionPagerAdapter);
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

    public class TestCollectionPagerAdapter extends FragmentPagerAdapter {
        public TestCollectionPagerAdapter (FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int i){
            Fragment fragment = new TestFragment();
            Bundle args = new Bundle();
            args.putInt(TestFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount(){
            return 100;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return "OBJECT" + (position + 1);
        }
    }

    public static class TestFragment extends Fragment {
        public static final String ARG_OBJECT = "object";

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.fragment_text, container, false);
            Bundle args = getArguments();
            ((TextView)rootView.findViewById(R.id.fragment_text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }
}
