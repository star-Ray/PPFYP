package com.example.arnold.fypproject.Activity;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arnold.fypproject.Connection.DatabaseContract;
import com.example.arnold.fypproject.Entity.Courier;
import com.example.arnold.fypproject.Entity.Sender;
import com.example.arnold.fypproject.Fragment.SampleFragment;
import com.example.arnold.fypproject.R;
import com.google.gson.Gson;


public class HomepageActivity extends ActionBarActivity implements SampleFragment.OnFragmentInteractionListener {
    public final static String EXTRA_MESSAGE = "com.example.arnold.fypproject.MESSAGE";
    Intent intent;
    Sender sender;
    Courier courier;

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homepage);
        Gson gson = new Gson();
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);

        sender = gson.fromJson(sharedPref.getString("sender", "sender_is_null"), Sender.class);
        courier = gson.fromJson(sharedPref.getString("courier", "courier_is_null"), Courier.class);

//        Setting users name in welcome message
        TextView textView = (TextView)findViewById(R.id.text_welcome_user);
        textView.setText("Welcome, " + courier.getName());

//        Loading fragment
        if (findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            SampleFragment sampleFragment = new SampleFragment();
            sampleFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, sampleFragment).commit();
        }

        if (getIntent().getStringExtra("login") != null){
            //Welcome toast
            Toast.makeText(this, "Welcome, " + courier.getName(), Toast.LENGTH_LONG).show();
        }

//        writeDatabaseTesting();
    }

//    Probably not using this method
    public void writeDatabaseTesting(){
        DatabaseContract.DatabaseHelper dbHelper = new DatabaseContract.DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TABLE_USER.COLUMN_NAME_COL1, "ARNOLD_VALUE_DB_TEST1");
        values.put(DatabaseContract.TABLE_USER.COLUMN_NAME_COL2, "ARNOLD_VALUE_DB_TEST2");
        values.put(DatabaseContract.TABLE_USER.COLUMN_NAME_COL3, "ARNOLD_VALUE_DB_TEST3");
        values.put(DatabaseContract.TABLE_USER.COLUMN_NAME_COL4, "ARNOLD_VALUE_DB_TEST4");
        values.put(DatabaseContract.TABLE_USER.COLUMN_NAME_COL5, "ARNOLD_VALUE_DB_TEST5");

        db.insert(DatabaseContract.TABLE_USER.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
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

    public void openMap(View view){
        Uri location = Uri.parse("https://www.google.com.sg/maps/dir/1.2771206,103.8564106/Suntec+Singapore+Convention+%26+Exhibition+Centre,+1+Raffles+Boulevard,+Suntec+City,+Singapore+039593/@1.2889031,103.8500284,15z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x31da19af38dd2bf3:0xd63e8cb2dacf54c7!2m2!1d103.857075!2d1.293455");
        intent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(intent);
    }

    public void goToTaskPage(View view){
        intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

    public void goToTest(View view){
        intent = new Intent(this, Test3Activity.class);
        startActivity(intent);
    }

    public void goToProfilePage(View view){
        intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("Arnold", "Arnold really rocks");
        startActivity(intent);
    }

    public void sendMessage(View view){
        intent = new Intent(this, ProfileActivity.class);
        TextView textView = (TextView)findViewById(R.id.text_username);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


}
