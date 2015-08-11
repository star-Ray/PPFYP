package fypproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fypproject.Entity.Item;
import fypproject.R;

public class ItemActivity extends ActionBarActivity {

    private static final String TAG = "arnono/ItemActivity";

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(NullPointerException e){
            Log.e(TAG, "ActionBar not available. " + e.getMessage());
            e.printStackTrace();
        }

        gson = new GsonBuilder().setDateFormat(getString(R.string.date_format)).create();

        Intent receivedIntent = getIntent();
        String jsonItem = receivedIntent.getStringExtra("item");
        Item item = gson.fromJson(jsonItem, Item.class);

        setViews(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setViews(Item item){
        String title = "Item ID: " + String.valueOf(item.getID() + " - Summary");
        try {
            getSupportActionBar().setTitle(title);
        }catch (NullPointerException e){
            Log.e(TAG, "ActionBar not available. " + e.getMessage());
            e.printStackTrace();
        }

        TextView itemId = (TextView) findViewById(R.id.content_itemId);
        TextView desc = (TextView) findViewById(R.id.content_description);
        TextView tagId = (TextView) findViewById(R.id.content_tagId);

        itemId.setText(String.valueOf(item.getID()));
        desc.setText(item.getDesc());
        tagId.setText(item.getNfcTag());
    }

}