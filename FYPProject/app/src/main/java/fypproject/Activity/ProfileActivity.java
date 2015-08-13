package fypproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fypproject.Entity.Courier;
import fypproject.R;


public class ProfileActivity extends ActionBarActivity {

    private static final String TAG = "arnono/ProfileAct";

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

//        initializing
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        intent = getIntent();
        gson = new GsonBuilder().setDateFormat(getString(R.string.date_format)).create();
        Log.d(TAG, "Initializing complete.");

        courier = gson.fromJson(sharedPref.getString("courier", null), Courier.class);

        //Setting textviews
        TextView courier_id = (TextView)findViewById(R.id.content_courierID);
        TextView courier_name = (TextView)findViewById(R.id.content_courierName);
        TextView courier_contactNo = (TextView)findViewById(R.id.content_courierContactNo);
        TextView courier_dateJoined = (TextView)findViewById(R.id.content_courierDateJoined);

        System.out.println("here1");

        courier_id.setText(String.valueOf(courier.getCourierId()));
        courier_name.setText(courier.getName());
        courier_contactNo.setText(courier.getContactNo());
//        courier_dateJoined.setText(courier.getCreateDate()); **not ready yet
        courier_dateJoined.setText("2 August 2015"); //temp text
        Log.d(TAG, "TextViews set");
    }
}
