package com.example.arnold.fypproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.arnold.fypproject.Entity.Courier;
import com.example.arnold.fypproject.R;
import com.google.gson.Gson;


public class ProfileActivity extends ActionBarActivity {
    Intent intent;
    Courier courier;
    Gson gson;

    protected void openCamera(View view){
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        intent = getIntent();
        gson = new Gson();

        courier = gson.fromJson(sharedPref.getString("courier", null), Courier.class);


        //Setting textviews
        TextView courier_id = (TextView)findViewById(R.id.content_courierID);
        TextView courier_name = (TextView)findViewById(R.id.content_courierName);
        TextView courier_contactNo = (TextView)findViewById(R.id.content_courierContactNo);
        TextView courier_dateJoined = (TextView)findViewById(R.id.content_courierDateJoined);
        courier_id.setText(String.valueOf(courier.getID()));
        courier_name.setText(courier.getName());
        courier_contactNo.setText(courier.getContactNo());
//        courier_dateJoined.setText(courier.getCreateDate()); **not ready yet
        courier_dateJoined.setText("2 August 2015"); //temp text
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
}
