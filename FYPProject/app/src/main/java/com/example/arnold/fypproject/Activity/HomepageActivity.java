package com.example.arnold.fypproject.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.arnold.fypproject.Entity.Courier;
import com.example.arnold.fypproject.Entity.Sender;
import com.example.arnold.fypproject.Fragment.SampleFragment;
import com.example.arnold.fypproject.R;
import com.google.gson.Gson;


public class HomepageActivity extends ActionBarActivity implements SampleFragment.OnFragmentInteractionListener {
    private Intent intent;
    private Sender sender;
    private Courier courier;
    private String[] drawerArr;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        Initializing
        Gson gson = new Gson();
        sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        sender = gson.fromJson(sharedPref.getString("sender", null), Sender.class);
        courier = gson.fromJson(sharedPref.getString("courier", null), Courier.class);

//        Drawer view
        drawerArr = new String[]{"My Profile", "NFC Page", "test1", "test2", "Test3NavScroll", "maps", "Settings", "Logout"};
        final ArrayAdapter<String> drawerAdapter = new ArrayAdapter<String>(this, R.layout.drawer_textview, drawerArr);
        final DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ListView listView = (ListView)findViewById(R.id.homepage_drawer);


        listView.setAdapter(drawerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selectItem(position);
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
                }
                drawerLayout.closeDrawers();
            }
        });

//        Load fragment
        if (findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            SampleFragment sampleFragment = new SampleFragment();
            sampleFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, sampleFragment).commit();
        }

//        Load toast
        if (getIntent().getStringExtra("login") != null){
//            Welcome toast
            Toast.makeText(this, "Welcome, " + courier.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    public void goToLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout confirmation").setMessage("Leaving so soon? We will miss you");
        builder.setPositiveButton("Yes, I'm leaving!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear().commit();
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
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

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

}
