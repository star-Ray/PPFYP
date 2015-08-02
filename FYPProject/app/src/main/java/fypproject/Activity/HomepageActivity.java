package fypproject.Activity;

import android.app.AlertDialog;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import fypproject.Entity.Courier;
import fypproject.Entity.Sender;
import fypproject.R;
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
    private android.support.v7.app.ActionBar actionBar;


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

        loadSwipeView(); //load swipe view
        loadActionBarTabs(); //load Action bar tabs
        loadDrawer(); //load Drawer
//        loadRecyclerView(); //load recycler view


//        Load welcome toast if entered from login page
        if (getIntent().getStringExtra("login") != null){
            Toast.makeText(this, "Welcome, " + courier.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onBackPressed() {
        editor.putString("action", "exit").apply();
        finish();
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
    public void goToTest1(){
        intent = new Intent(this, Test2Activity.class);
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
    public void goToTask(View view){
        intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }
    public void goToBidding(){
        intent = new Intent(this, BiddingActivity.class);
        startActivity(intent);
    }

    public void loadDrawer(){
//        Drawer view
        String[] drawerArr = new String[]{"My Profile", "Bidding", "NFC Page", "WriteNFC", "test1", "maps", "Settings", "Logout"};
        final ArrayAdapter<String> drawerAdapter = new ArrayAdapter<String>(this, R.layout.drawer_textview, drawerArr);
        final DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        ListView listView = (ListView)findViewById(R.id.homepage_drawer);
        listView.setAdapter(drawerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemTitle = drawerAdapter.getItem(position);
                switch (itemTitle) {
                    case "My Profile":
                        goToMyProfilePage();
                        break;
                    case "Bidding":
                        goToBidding();
                        break;
                    case "NFC Page":
                        goToNFCPage();
                        break;
                    case "Settings":
                        break;
                    case "Logout":
                        goToLogout();
                        break;
                    case "test1":
                        goToTest1();
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
        String[] tabList = new String[]{"Previous", "Current", "Future"};
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
        homepageTabsPagerAdapter = new HomepageTabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homepageTabsPagerAdapter);
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
    }
    public static class HomepageFragment extends android.support.v4.app.Fragment {
        public static final String ARG_OBJECT = "object";
        private RecyclerView recyclerView;
        private RecyclerView.Adapter taskListAdapter;
        private RecyclerView.LayoutManager layoutManager;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.fragment_homepage, container, false);
            Bundle args = getArguments();
//            ((TextView)rootView.findViewById(R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));

            loadRecyclerView(rootView);

            return rootView;
        }

        public void loadRecyclerView(View view){
            recyclerView = (RecyclerView) view.findViewById(R.id.tasklist_recycler);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);

            // use a linear layout manager
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            // specify an adapter (see also next example)
            String[] myDataset = new String[]{"bye1", "bye2", "bye3"};
            taskListAdapter = new TaskListAdapter(myDataset);
            recyclerView.setAdapter(taskListAdapter);
        }
    }

    public static class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
        private String[] dataSet;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            public View view;

            public ViewHolder(View v) {
                super(v);
                view = v;
//                textView2 = (TextView) v.findViewById(R.id.textView2);
            }
//            public TextView getTitle(){
//                return textView2;
//            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public TaskListAdapter(String[] myDataset) {
            dataSet = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_currenttask, parent, false); // create a new view
            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
//            holder.getTitle().setText(mDataset[position]);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return dataSet.length;
        }

    }
}
