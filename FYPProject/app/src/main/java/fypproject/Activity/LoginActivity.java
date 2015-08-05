package fypproject.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import fypproject.Entity.Courier;
import fypproject.Entity.Sender;
import fypproject.R;


public class LoginActivity extends Activity{

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        initializing
        sharedPref = this.getSharedPreferences(getString(R.string.app_key), MODE_PRIVATE);
        editor = sharedPref.edit();

        String courier = sharedPref.getString("courier", null);
        if(courier != null){
            Intent intent = new Intent(this, HomepageActivity.class);
            startActivity(intent);
        }
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

        Intent intent = new Intent(this, HomepageActivity.class);

        EditText input_username = (EditText)findViewById(R.id.login_username);
        EditText input_password = (EditText)findViewById(R.id.login_password);
        String username = input_username.getText().toString();
        String password = input_password.getText().toString();

//        ****** Create test user ******
        Courier courier = createTestCourier();

        // validation of login credentials
        if(courier.getUsername().equals(username) && courier.getPasswordSALT().equals(password)){
            Gson gson = new Gson();

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
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    public Courier createTestCourier(){
//        Courier courier = new Courier(1,1,"Arnold Lee", "arnold.lee.2013", "123", "123", "98765446", "Hougang", "no_remarks", null, 1, null);
        Courier courier = new Courier(1,1,"Arnold Lee", "arnold.lee.2013", "123", "123", "98765446", "Hougang", "no_remarks", "company", null);
        return courier;
    }

}
