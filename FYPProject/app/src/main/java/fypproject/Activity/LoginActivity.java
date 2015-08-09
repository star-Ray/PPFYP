package fypproject.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import fypproject.Connection.NetworkSingleton;
import fypproject.Entity.Courier;
import fypproject.R;


public class LoginActivity extends Activity{

    private static final String TAG = "arnono/LoginActivity";

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private NetworkSingleton networkSingleton;

    private Courier courier;

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
            startActivity(intent);
        }
        networkSingleton = NetworkSingleton.getInstance(this.getApplicationContext());
        Log.d(TAG, "NetworkSingleton created");

//        courier = getCourierFromWebService(1);
        Log.d(TAG, "Courier1: " + courier);
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

    // Login button press
    public void sendMessage(View view){
        Log.d(TAG, "sendMessage called");
        Log.d(TAG, "Courier2: " + courier);
        Log.d(TAG, "Threadname 1: " + Thread.currentThread().getName());

        EditText inputUsername = (EditText)findViewById(R.id.login_username);
        EditText inputPassword = (EditText)findViewById(R.id.login_password);
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        try {
            boolean authenticate = authenticateUser(username, password);
            Log.d(TAG, "User authentication: " + authenticate);
            if (authenticate){

//                getCourierFromWebService(1);
                Log.d(TAG, "Threadname 5: " + Thread.currentThread().getName());
//                courier = createTestCourier(); // create test courier
                Log.d(TAG, "Courier3: " + courier);
                courier = createTestCourier();
                if(courier == null){
                    Log.d(TAG, "Courier is null exception thrown.");
                    throw new Exception("Courier is null");
                }

                editor.putString("courier", gson.toJson(courier));
                editor.apply();

                Intent intent = new Intent(this, HomepageActivity.class);
                intent.putExtra("login", "");
                startActivity(intent);

            }else{
                throw new Exception(getString(R.string.dialog_message_login_fail));
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "Error occurred. Message: " + e.getMessage());

            // Alert Dialog
            alertBuilder.setTitle(R.string.dialog_title_login_fail).setMessage(e.getMessage());
            alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        }
    }

    public static Courier createTestCourier(){
        Courier courier = new Courier(1,1,"Arnold Lee", "arnold.lee.2013", "98765446", "Hougang", "no_remarks", "company", "2015-08-18T00:00:00");
        return courier;
    }

    public boolean authenticateUser(String username, String password){
        if(username.equals("zhurou.fan.2015") && password.equals("123")){
            return true;
        }
        return false;
    }

    public void getCourierFromWebService(final int courierID) {
        Log.d(TAG, "getCourierFromWebService: start");

        final Courier[] arrCourier = new Courier[1];
        final String url = "http://eyetem-huiqiong2013.rhcloud.com/SimTech/admin/GetCourierByIdServlet?input={%22courierId%22:" + courierID + "}";

        Log.d(TAG, "Url: " + url);



        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread runnable is running.");
                Log.d(TAG, "Threadname 2: " + Thread.currentThread().getName());
                RequestFuture<JSONObject> future = RequestFuture.newFuture();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, future, future);
//                networkSingleton.addToRequestQueue(jsonObjectRequest);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.start();
                queue.add(jsonObjectRequest);
                Log.d(TAG, "JsonObjectRequest added to queue!");

                try {
                    Log.d(TAG, "Threadname 3: " + Thread.currentThread().getName());
//                    JSONObject jsonObject = future.get(10, TimeUnit.SECONDS); // last 5 seconds
                    JSONObject jsonObject = future.get();
                    Log.d(TAG, "getCourierFromWebService: response received.");
                    String content = jsonObject.getString("message");
                    Courier jsonCourier = gson.fromJson(content, Courier.class);
                    Log.d(TAG, "getCourierFromWebService: jsonCourier: " + jsonCourier);
                    Log.d(TAG, "getCourierFromWebService: jsonCourierName: " + jsonCourier.toString());
                    arrCourier[0] = jsonCourier;
                    System.out.println("arr username: " + arrCourier[0].getUsername());
                } catch (InterruptedException | ExecutionException  | JSONException  e) {
                    e.printStackTrace();
                }
                queue.stop();
            }
        });

        try {
            Log.d(TAG, "Threadname 4: " + Thread.currentThread().getName());
            threadA.start();
            threadA.join(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Log.d(TAG, "getCourierFromWebService: end");
//        return arrCourier[0];
    }

}





