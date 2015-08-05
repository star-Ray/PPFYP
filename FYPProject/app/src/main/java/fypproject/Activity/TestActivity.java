package fypproject.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.GpsStatus;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import fypproject.Connection.NetworkSingleton;
import fypproject.R;

public class TestActivity extends ActionBarActivity {

    private NetworkSingleton networkSingleton;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        volleyQueueNetworkTesting();
        telephoneTesting();
        listViewTesting(); //listview testing

    }

    public void volleyQueueNetworkTesting(){

        final TextView textview = (TextView)findViewById(R.id.test_name);

        // Instantiate the cache
//        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
//        Network network = new BasicNetwork(new HurlStack());

        final ImageView imageView = (ImageView)findViewById(R.id.imageView);

        //RequestQueue
        networkSingleton = NetworkSingleton.getInstance(this.getApplicationContext());
        imageLoader = networkSingleton.getImageLoader();
        String url = "http://maps.googleapis.com/maps/api/directions/json?origin=singapore&destination=malaysia";
        String imageUrl = "http://animalia-life.com/data_images/dog/dog2.jpg";
        String jsonUrl = "http://eyetem-huiqiong2013.rhcloud.com/SimTech/admin/GetCourierByIdServlet?input={%22courierId%22:1}";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textview.setText("Response is: " + response.substring(0, 2000));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textview.setText("That didn't work!");
            }
        });

        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, jsonUrl, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject jsonObject) {
                textview.setText(jsonObject.toString());
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // TODO
            }
        });

        imageLoader.get(imageUrl, ImageLoader.getImageListener(imageView, R.drawable.error, R.drawable.error));


        // Add the request to the RequestQueue.
        networkSingleton.addToRequestQueue(stringRequest);
//        networkSingleton.addToRequestQueue(imageRequest);
        networkSingleton.addToRequestQueue(jsonRequest);
    }

    public void telephoneTesting(){
        // Testing codes to retrieve phone number
        TextView myNum = (TextView)findViewById(R.id.number123);
        TelephonyManager tMgr = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
//        String mPhoneNumber = tMgr.getSimSerialNumber();
        String mPhoneNumber = tMgr.getDeviceId();
        System.out.println("telephonemanager: " + tMgr.toString());
        System.out.println("Phonenumber: " + mPhoneNumber);
        if (mPhoneNumber.equals("")){
            mPhoneNumber = "nothing";
        }
        myNum.setText(mPhoneNumber);
    }

    public void listViewTesting(){
        //listview testing
        String[] arr = {"hello","hello2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        ListView listview = (ListView)findViewById(R.id.listview1);
        listview.setAdapter(adapter);
    }
}
