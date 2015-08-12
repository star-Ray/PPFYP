package fypproject.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fypproject.Activity.HomepageFragments.BallotFragment;
import fypproject.Activity.HomepageFragments.CompletedFragment;
import fypproject.Activity.HomepageFragments.OngoingFragment;
import fypproject.Entity.Courier;
import fypproject.R;

public class HomepageActivity extends ActionBarActivity {

    private static final String TAG = "arnono/HomepageActivity";

    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    private ViewPager viewPager;
    private android.support.v7.app.ActionBar actionBar;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        Initializing
        Gson gson = new GsonBuilder().setDateFormat(getString(R.string.date_format)).create();
        sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();
        Courier courier = gson.fromJson(sharedPref.getString("courier", null), Courier.class);

        loadSwipeView(); //load swipe view
        loadActionBarTabs(); //load Action bar tabs
        loadDrawer(); //load Drawer

//        Load welcome toast if entered from login page
        if (getIntent().getStringExtra("login") != null){
            Toast.makeText(this, "Welcome, " + courier.getName(), Toast.LENGTH_SHORT).show();
        }

        viewPager.setCurrentItem(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawers();
                }else{
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        editor.putString("action", "exit").apply();
        finish();
    }

    public void processLogout(){
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

    public void loadDrawer(){

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_18dp);

//        Drawer view
        String[] drawerArr = new String[]{"My Profile", "Read NFC Tag", "Write to NFC Tag", "Maps", "test1", "test2", "Logout"};
        final ArrayAdapter<String> drawerAdapter = new ArrayAdapter<String>(this, R.layout.drawer_item, drawerArr);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        ListView listView = (ListView)findViewById(R.id.homepage_drawer);
        listView.setAdapter(drawerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemTitle = drawerAdapter.getItem(position);
                Context context = getApplicationContext();
                Intent intent = null;
                if (itemTitle.equals("Logout")) {
                    processLogout();
                } else {
                    switch (itemTitle) {
                        case "My Profile":
                            intent = new Intent(context, ProfileActivity.class);
                            break;
                        case "Read NFC Tag":
                            intent = new Intent(context, ReadNFCActivity.class);
                            break;
                        case "test1":
                            intent = new Intent(context, TestActivity.class);
                            break;
                        case "test2":
                            intent = new Intent(context, Test2Activity.class);
                            break;
                        case "Maps":
                            intent = new Intent(context, MapActivity.class);
                            break;
                        case "Write to NFC Tag":
                            intent = new Intent(context, WriteNFCActivity.class);
                            break;
                        default:
                            Toast.makeText(context, "Sorry. The page could not be started.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Default value is run on drawer switch.");
                            break;
                    }
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                }
            }
        });
    }
    public void loadActionBarTabs(){
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
        android.support.v7.app.ActionBar.TabListener tabListener = new android.support.v7.app.ActionBar.TabListener() {
            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            }
            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };
//         Add 3 tabs, specifying the tab's text and TabListener
        String[] tabList = new String[]{"Completed", "Ongoing", "Ballot"};
        for (int i = 0; i < tabList.length; i++) {
            actionBar.addTab(actionBar.newTab().setText(tabList[i]).setTabListener(tabListener));
        }
    }
    public void loadSwipeView(){
        viewPager = (ViewPager) findViewById(R.id.homepage_pager);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        HomepageTabsPagerAdapter homepageTabsPagerAdapter = new HomepageTabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homepageTabsPagerAdapter);
    }

    public static class HomepageTabsPagerAdapter extends FragmentPagerAdapter {
        public HomepageTabsPagerAdapter (FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int i){
            Fragment fragment = null;
            switch(i){
                case 0:
                    fragment = new CompletedFragment();
                    break;
                case 1:
                    fragment = new OngoingFragment();
                    break;
                case 2:
                    fragment = new BallotFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3; // to be adjusted according to the number of tabs
        }
    }
}
