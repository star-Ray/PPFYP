package com.example.arnold.fypproject.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arnold.fypproject.R;

public class Test2Activity extends FragmentActivity {

    private String[] drawerArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        drawerArr = new String[]{"test1", "Logout"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.drawer_textview, drawerArr);
        ListView listView = (ListView)findViewById(R.id.left_drawer);
        listView.setAdapter(adapter);

    }
}
