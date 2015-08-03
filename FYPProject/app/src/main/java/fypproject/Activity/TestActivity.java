package fypproject.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import fypproject.R;

public class TestActivity extends ActionBarActivity {

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
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        final ImageView imageView = (ImageView)findViewById(R.id.imageView);

        //RequestQueue
        RequestQueue queue = new RequestQueue(cache, network);
        queue.start();
        String url = "http://maps.googleapis.com/maps/api/directions/json?origin=singapore&destination=malaysia";
        String imageUrl = "http://animalia-life.com/data_images/dog/dog2.jpg";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        textview.setText("Response is: "+ response.substring(0,500));
                        textview.setText("Response is: " + response.substring(0, 2000));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textview.setText("That didn't work!");
            }
        });

        ImageRequest imageRequest = new ImageRequest(imageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(R.drawable.error);
                    }
                });


        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.add(imageRequest);
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
