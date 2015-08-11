package fypproject.Activity;

import android.app.AlertDialog;
import android.content.Context;
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
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import fypproject.Activity.HomepageFragments.BallotFragment;
import fypproject.Activity.HomepageFragments.CompletedFragment;
import fypproject.Activity.HomepageFragments.OngoingFragment;
import fypproject.Entity.Courier;
import fypproject.Entity.Task;
import fypproject.R;

public class HomepageActivity extends ActionBarActivity {

    private static final String TAG = "arnono/HomepageActivity";

    private Courier courier;
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    private static Gson gson;
    private HomepageTabsPagerAdapter homepageTabsPagerAdapter;
    private ViewPager viewPager;
    private android.support.v7.app.ActionBar actionBar;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        Initializing
        gson = new GsonBuilder().setDateFormat(getString(R.string.date_format)).create();
        sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();
        courier = gson.fromJson(sharedPref.getString("courier", null), Courier.class);

        loadSwipeView(); //load swipe view
        loadActionBarTabs(); //load Action bar tabs
        loadDrawer(); //load Drawer

//        Load welcome toast if entered from login page
        if (getIntent().getStringExtra("login") != null){
            Toast.makeText(this, "Welcome, " + courier.getName(), Toast.LENGTH_SHORT).show();
        }
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
        viewPager.setCurrentItem(1);
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
        String[] drawerArr = new String[]{"My Profile", "NFC Page", "WriteNFC", "test1", "test2", "maps", "Logout"};
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
                        case "NFC Page":
                            intent = new Intent(context, ReadNFCActivity.class);
                            break;
                        case "test1":
                            intent = new Intent(context, TestActivity.class);
                            break;
                        case "test2":
                            intent = new Intent(context, Test2Activity.class);
                            break;
                        case "maps":
                            Uri location = Uri.parse("https://www.google.com.sg/maps/dir/1.2771206,103.8564106/Suntec+Singapore+Convention+%26+Exhibition+Centre,+1+Raffles+Boulevard,+Suntec+City,+Singapore+039593/@1.2889031,103.8500284,15z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x31da19af38dd2bf3:0xd63e8cb2dacf54c7!2m2!1d103.857075!2d1.293455");
                            intent = new Intent(Intent.ACTION_VIEW, location);
                            break;
                        case "WriteNFC":
                            intent = new Intent(context, WriteNFCActivity.class);
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
        homepageTabsPagerAdapter = new HomepageTabsPagerAdapter(getSupportFragmentManager());
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

//    public static class HomepageFragment extends android.support.v4.app.Fragment {
////        public static final String ARG_OBJECT = "object";
//        private static RecyclerView recyclerView;
//        private static RecyclerView.Adapter taskListAdapter;
//        private static RecyclerView.LayoutManager layoutManager;
//
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//            View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
//
//            loadRecyclerView(rootView);
//            return rootView;
//        }
//
//        public void loadRecyclerView(View view){
//            recyclerView = (RecyclerView) view.findViewById(R.id.taskRecyclerView);
//
//            // use this setting to improve performance if you know that changes
//            // in content do not change the layout size of the RecyclerView
//            recyclerView.setHasFixedSize(true);
//
//            // use a linear layout manager
//            layoutManager = new LinearLayoutManager(getActivity());
//            recyclerView.setLayoutManager(layoutManager);
//
//            // specify an adapter (see also next example)
//            ArrayList<Task> taskList = TestCreator.createTestTasks1();
//            ArrayList<Task> myDataset = taskList;
//            taskListAdapter = new HomepageTaskListAdapter(myDataset);
//            recyclerView.setAdapter(taskListAdapter);
//        }
//    }

    // recycler view
//    public static class HomepageTaskListAdapter extends RecyclerView.Adapter<HomepageTaskListAdapter.ViewHolder> {
//        private ArrayList<Task> dataSet;
//
//        // Provide a reference to the views for each data item
//        // Complex data items may need more than one view per item, and
//        // you provide access to all the views for a data item in a view holder
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public View view;
//            public TextView recipient;
//            public TextView address;
//            public TextView time;
//            public TextView orderNo;
//
//            public ViewHolder(View v) {
//                super(v);
//                view = v;
//
//                recipient = (TextView)view.findViewById(R.id.content_receiver);
//                address = (TextView)view.findViewById(R.id.content_address);
//                time = (TextView)view.findViewById(R.id.content_time);
//                orderNo = (TextView)view.findViewById(R.id.content_orderNo);
//
//                v.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Context context = v.getContext();
//                        int position = getPosition();
//                        Task selectedTask = dataSet.get(position);
//                        Log.d(TAG, "Position selected: " + position);
//
//                        Intent intent = new Intent(context, TaskActivity.class);
//                        intent.putExtra("task", gson.toJson(selectedTask));
//                        context.startActivity(intent);
//                    }
//                });
//            }
//        }
//
////        constructor
//        public HomepageTaskListAdapter(ArrayList<Task> myDataset) {
//            dataSet = myDataset;
//        }
//
//        // Create new views (invoked by the layout manager)
//        @Override
//        public HomepageTaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ongoing_tasklist, parent, false); // create a new view
//            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
//            return vh;
//        }
//
//        // Replace the contents of a view (invoked by the layout manager)
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            // - get element from your dataset at this position
//            // - replace the contents of the view with that element
//
//            holder.recipient.setText(dataSet.get(position).getReceiverName());
//            holder.address.setText(dataSet.get(position).getEndLocation());
////            holder.time.setText(dataSet.get(position).getPlanEndTime().toString());
//            holder.orderNo.setText(String.valueOf(dataSet.get(position).getID()));
//        }
//
//        // Return the size of your dataset (invoked by the layout manager)
//        @Override
//        public int getItemCount() {
//            return dataSet.size();
//        }
//
//    }
}
