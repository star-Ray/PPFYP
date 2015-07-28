package com.example.arnold.fypproject.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arnold.fypproject.Entity.Courier;
import com.example.arnold.fypproject.Entity.Sender;
import com.example.arnold.fypproject.R;
import com.google.gson.Gson;


public class HomepageActivity extends ActionBarActivity {
    private Intent intent;
    private Sender sender;
    private Courier courier;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private HomepageTabsPagerAdapter homepageTabsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        Initializing
        gson = new Gson();
        sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();
        sender = gson.fromJson(sharedPref.getString("sender", null), Sender.class);
        courier = gson.fromJson(sharedPref.getString("courier", null), Courier.class);

//        ActionBarTabs
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        Toast.makeText(this, actionBar.toString(), Toast.LENGTH_LONG).show();
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
        android.support.v7.app.ActionBar.TabListener tabListener = new android.support.v7.app.ActionBar.TabListener() {
            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            }
            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };
//         Add 3 tabs, specifying the tab's text and TabListener
        String[] tabList = new String[]{"Previous", "Current", "Future"};
        for (int i = 0; i < tabList.length; i++) {
            actionBar.addTab(actionBar.newTab().setText(tabList[i]).setTabListener(tabListener));
        }


//        Load swipe view
        viewPager = (ViewPager)findViewById(R.id.pager_test);
        homepageTabsPagerAdapter = new HomepageTabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homepageTabsPagerAdapter);

        loadDrawer(); //load Drawer

//        Load welcome toast if entered from login page
        if (getIntent().getStringExtra("login") != null){
            Toast.makeText(this, "Welcome, " + courier.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    public void goToLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout confirmation").setMessage("Leaving so soon? We will miss you");
        builder.setPositiveButton("Yes, I'm leaving!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                editor.clear().apply();
                finish();
            }
        });
        builder.setNegativeButton("No, I'm staying!", null);
        builder.create().show();
    }
    public void goToMaps(){
        Uri location = Uri.parse("https://www.google.com.sg/maps/dir/1.2771206,103.8564106/Suntec+Singapore+Convention+%26+Exhibition+Centre,+1+Raffles+Boulevard,+Suntec+City,+Singapore+039593/@1.2889031,103.8500284,15z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x31da19af38dd2bf3:0xd63e8cb2dacf54c7!2m2!1d103.857075!2d1.293455");
        intent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(intent);
    }
    public void goToTest3(){
        intent = new Intent(this, Test3Activity.class);
        startActivity(intent);
    }
    public void goToTest2(){
        intent = new Intent(this, Test2Activity.class);
        startActivity(intent);
    }
    public void goToTest1(){
        intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
    public void goToNFCPage(){
        intent = new Intent(this, ReadNFCActivity.class);
        startActivityForResult(intent, 1);
    }
    public void goToMyProfilePage(){
        intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("Arnold", "Arnold really rocks");
        startActivity(intent);
    }
    public void goToWriteNFC(){
        intent = new Intent(this, WriteNFCActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        editor.putString("action", "exit").apply();
        finish();
    }

    public void loadDrawer(){
//        Drawer view
        String[] drawerArr = new String[]{"My Profile", "NFC Page", "WriteNFC", "test1", "test2", "Test3NavScroll", "maps", "Settings", "Logout"};
        final ArrayAdapter<String> drawerAdapter = new ArrayAdapter<String>(this, R.layout.drawer_textview, drawerArr);
        final DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        ListView listView = (ListView)findViewById(R.id.homepage_drawer);
        listView.setAdapter(drawerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemTitle = drawerAdapter.getItem(position);
                switch(itemTitle){
                    case "My Profile":
                        goToMyProfilePage();
                        break;
                    case "NFC Page":
                        goToNFCPage();
                        break;
                    case "Settings":
                        break;
                    case "Logout":
                        goToLogout();
                        break;
                    case "Test3NavScroll":
                        goToTest3();
                        break;
                    case "test1":
                        goToTest1();
                        break;
                    case "test2":
                        goToTest2();
                        break;
                    case "maps":
                        goToMaps();
                        break;
                    case "WriteNFC":
                        goToWriteNFC();
                        break;
                }
                drawerLayout.closeDrawers();
            }
        });
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
            return "OBJECT " + (position + 1);
        }
    }

    public static class HomepageFragment extends android.support.v4.app.Fragment {
        public static final String ARG_OBJECT = "object";

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.fragment_text, container, false);
            Bundle args = getArguments();
            ((TextView)rootView.findViewById(R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }
}
