package fypproject.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
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

        String courier = sharedPref.getString("courier", null);
        if(courier != null){
            Intent intent = new Intent(this, HomepageActivity.class);
            startActivity(intent);
        }
        networkSingleton = NetworkSingleton.getInstance(this.getApplicationContext());
        System.out.println("LoginActivity: NetworkSingleton created");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sharedPref.getString("action", "null").equals("exit")){
            editor.remove("action").apply();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    // Login button press
    public void sendMessage(View view){
        System.out.println("LoginActivity: sendMessage called.");

        EditText inputUsername = (EditText)findViewById(R.id.login_username);
        EditText inputPassword = (EditText)findViewById(R.id.login_password);
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

//        setJSONCourier(1);

//        ****** Create test user ******
        courier = createTestCourier();

        // validation of login credentials
        if(username.length() != 0 && password.length() != 0 && courier != null &&
                courier.getUsername().equals(username) && courier.getPasswordSALT().equals(password)){

            editor.putString("courier", gson.toJson(courier));
            editor.apply();

            Intent intent = new Intent(this, HomepageActivity.class);
            intent.putExtra("login", "");
            startActivity(intent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_title_login_fail).setMessage(R.string.dialog_message_login_fail);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public Courier createTestCourier(){
        Courier courier = new Courier(1,1,"Arnold Lee", "arnold.lee.2013", "123", "123", "98765446", "Hougang", "no_remarks", "company", "2015-08-18T00:00:00");
        return courier;
    }

//    public void setJSONCourier(int courierID){
//        System.out.println("LoginActivity: setJSONCourier start");
//        final String url = "http://eyetem-huiqiong2013.rhcloud.com/SimTech/admin/GetCourierByIdServlet?input={%22courierId%22:" + courierID + "}";
//        RequestFuture<JSONObject> future = RequestFuture.newFuture();
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, future, future);
//        networkSingleton.addToRequestQueue(jsonObjectRequest);
//        try {
//            JSONObject jsonObject = future.get(1, TimeUnit.SECONDS); // last 2 seconds
//            System.out.println("LoginActivity/setJSONCourier/onResponse: Status is valid." );
//            String content = jsonObject.getString("message");
//            courier = gson.fromJson(content, Courier.class);
//            System.out.println("LoginActivity/setJSONCourier/onResponse: Courier is set." );
//        } catch (ExecutionException | InterruptedException | TimeoutException | JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println("LoginActivity: setJSONCourier end");
//    }


}
