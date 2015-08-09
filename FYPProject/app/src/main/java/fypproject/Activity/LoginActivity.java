package fypproject.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import fypproject.Connection.NetworkSingleton;
import fypproject.Entity.Courier;
import fypproject.R;
import fypproject.TestCreator;


public class LoginActivity extends Activity{

    private static final String TAG = "arnono/LoginActivity";

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private NetworkSingleton networkSingleton;

//    private Courier courier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        initializing
        sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        String strCourier = sharedPref.getString("courier", null);
        if(strCourier != null){
            Intent intent = new Intent(this, HomepageActivity.class);
            Log.i(TAG, "Opening homepage");
            startActivity(intent);
        }
        networkSingleton = NetworkSingleton.getInstance(this.getApplicationContext());
        Log.d(TAG, "NetworkSingleton created");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sharedPref.getString("action", "null").equals("exit")){
            editor.remove("action").apply();
            finish();
        }
        EditText inputUsername = (EditText)findViewById(R.id.login_username);
        EditText inputPassword = (EditText)findViewById(R.id.login_password);
        inputPassword.setText("");

        inputUsername.setText("zhurou.fan.2015");
        inputPassword.setText("123");
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public boolean authenticateUser2(String username, String password){
        if(username.equals("zhurou.fan.2015") && password.equals("123")){
            return true;
        }
        return false;
    }

    public void authenticateUser(View view) {
        Log.i(TAG, "authenticateUser: start");
        final Context context = getApplicationContext();

//        getting user input
        EditText inputUsername = (EditText)findViewById(R.id.login_username);
        EditText inputPassword = (EditText)findViewById(R.id.login_password);
        final String username = inputUsername.getText().toString();
        final String password = inputPassword.getText().toString();

        try {
            if (username.length() == 0 || password.length() == 0) { // checking for invalid inputs
                Log.d(TAG, "Courier is null exception thrown.");
                throw new Exception("Courier is null");
            }

//            *********************** testing only ***********************
            final String url = "http://eyetem-huiqiong2013.rhcloud.com/SimTech/admin/GetCourierByIdServlet?input={%22courierId%22:" + 3 + "}";
//            *********************** testing only ***********************

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.d(TAG, "JsonObjectRequest received!");

//                    *********************** testing only ***********************

                    boolean authenticate = authenticateUser2(username, password);
                    if (authenticate){
                        Courier courier = TestCreator.createTestCourier();

                        editor.putString("courier", gson.toJson(courier));
                        editor.apply();

                        Intent intent = new Intent(context, HomepageActivity.class);
                        intent.putExtra("login", "");
                        Log.i(TAG, "Opening homepage");
                        startActivity(intent);
                    }else{
                        runAlertDialog("Authentication failed");
                    }

//                    *********************** testing only ***********************
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e(TAG, "Error occurred at processLogin.");
                }
            });

            networkSingleton.addToRequestQueue(jsonObjectRequest);
            Log.i(TAG, "Request at processLogin added successfully!");

        } catch (Exception e){
            Log.e(TAG, "Error occurred at processLogin. Message: " + e.getMessage());
            e.printStackTrace();

            runAlertDialog(e.getMessage()); // Alert Dialog
        } finally {
            Log.i(TAG, "authenticateUser: end");
        }
    }

    public void runAlertDialog(String message){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this.getApplicationContext());
        alertBuilder.setTitle(R.string.dialog_title_login_fail).setMessage(message);
        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}





