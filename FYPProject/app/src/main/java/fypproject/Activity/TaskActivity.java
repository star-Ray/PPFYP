package fypproject.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fypproject.Entity.Task;
import fypproject.R;

public class TaskActivity extends ActionBarActivity {

    public static Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

//        initializing
        gson = new GsonBuilder().setDateFormat(getString(R.string.date_format)).create();
        getSupportActionBar().show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent receivedIntent = getIntent();
        String jsonTask = receivedIntent.getStringExtra("task");
        Task task = gson.fromJson(jsonTask, Task.class);

        setViews(task);
    }

    private void setViews(Task task){
        TextView receiver = (TextView)findViewById(R.id.content_receiver);
        TextView contactNo = (TextView)findViewById(R.id.content_contactNumber);
        TextView address = (TextView)findViewById(R.id.content_address);

        receiver.setText(task.getReceiverName());
        contactNo.setText(task.getReceiverContact());
        address.setText(task.getEndLocation());
    }
}
