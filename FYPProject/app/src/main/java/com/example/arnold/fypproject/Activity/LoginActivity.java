package com.example.arnold.fypproject.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.example.arnold.fypproject.Entity.Courier;
import com.example.arnold.fypproject.Entity.Item;
import com.example.arnold.fypproject.Entity.Sender;
import com.example.arnold.fypproject.Entity.Task;
import com.example.arnold.fypproject.R;
import com.google.gson.Gson;

import java.util.ArrayList;


public class LoginActivity extends Activity{
    public final static String EXTRA_MESSAGE = "com.example.arnold.fypproject.MESSAGE";
    public final static String KEY = "com.example.arnold.fypproject";

    // Login button press
    public void sendMessage(View view){
        Intent intent = new Intent(this, HomepageActivity.class);
        EditText input_username = (EditText)findViewById(R.id.login_text_username);
        EditText input_password = (EditText)findViewById(R.id.login_text_password);
        String username = input_username.getText().toString();
        String password = input_password.getText().toString();

//        Create test user
        Sender sender = createTestSender();
        Courier courier = createTestCourier();

        // validation of login credentials
        if(sender.getUsername().equals(username) && sender.getPasswordSALT().equals(password)){
            SharedPreferences sharedPref = this.getSharedPreferences(KEY, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            Gson gson = new Gson();

            editor.putString("sender", gson.toJson(sender));
            editor.putString("courier", gson.toJson(courier));

            editor.apply();
            intent.putExtra("login", "");
            startActivity(intent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_title_login_fail).setMessage(R.string.dialog_message_login_fail);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public Sender createTestSender(){
        Sender sender = new Sender(1, 1, 1, "Ray_Sender", "98366640", "arnold.lee.2013", "123", "123", "boy", null);
        ArrayList<Item> itemList = new ArrayList<Item>();
        itemList.add(new Item(1,1,1,2.0,"Banana","dimen","image","nfcTagID", "barcode", "no remarks", null));
        itemList.add(new Item(2,1,1,2.0,"Potato","dimen","image","nfcTagID", "barcode", "no remarks", null));

        Task task = new Task(1,1,1,1,"Task1", "4444444", "startL", "endL", "sign", "OTP", "Ontime", "no remarks",
                null, null, null, null, null, itemList);
        ArrayList<Task> taskList = new ArrayList<Task>();
        taskList.add(task);
        sender.setTaskList(taskList);

        return sender;
    }

    public Courier createTestCourier(){
        Courier courier = new Courier(1,1,"Arnold_Courier", "98765446", "Hougang", "no_remarks", null);
        return courier;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}
