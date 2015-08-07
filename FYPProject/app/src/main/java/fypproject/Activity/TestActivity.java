package fypproject.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.GpsStatus;
import android.net.Network;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
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
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import fypproject.Connection.NetworkSingleton;
import fypproject.R;

public class TestActivity extends ActionBarActivity {

    private NetworkSingleton networkSingleton;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        networkSingleton = NetworkSingleton.getInstance(this);

        volleyQueueNetworkTesting();
//        telephoneTesting();
//        listViewTesting(); //listview testing

    }

    public void volleyQueueNetworkTesting(){

//        final TextView textview = (TextView)findViewById(R.id.test_name);
//
//        // Instantiate the cache
////        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
//        // Set up the network to use HttpURLConnection as the HTTP client.
////        Network network = new BasicNetwork(new HurlStack());
//
//        final ImageView imageView = (ImageView)findViewById(R.id.imageView);
//
//        //RequestQueue
//        networkSingleton = NetworkSingleton.getInstance(this.getApplicationContext());
//        imageLoader = networkSingleton.getImageLoader();
//        String url = "http://maps.googleapis.com/maps/api/directions/json?origin=singapore&destination=malaysia";
//        String imageUrl = "http://animalia-life.com/data_images/dog/dog2.jpg";
//        String jsonUrl = "http://eyetem-huiqiong2013.rhcloud.com/SimTech/admin/GetCourierByIdServlet?input={%22courierId%22:1}";

        // Request a string response from the provided URL.
//        RequestFuture<String> future = RequestFuture.newFuture();
//        StringRequest request = new StringRequest("http://eyetem-huiqiong2013.rhcloud.com/SimTech/admin/GetCourierByIdServlet?input={%22courierId%22:1}", future, future);
//        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
//        queue.start();
//        queue.add(request);
//
//        try {
//            String q = future.get(5, TimeUnit.SECONDS);
//            System.out.println("checkthis: " + q);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }

//        try {
//            JSONObject obj = new ThreadB(this.getApplicationContext()).execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("running...");
                RequestFuture<JSONObject> future = RequestFuture.newFuture();
                JsonObjectRequest request = new JsonObjectRequest("http://eyetem-huiqiong2013.rhcloud.com/SimTech/admin/GetCourierByIdServlet?input={%22courierId%22:1}", null, future, future);
                networkSingleton.addToRequestQueue(request);
                System.out.print("added...");
                try {
                    System.out.println("waiting...");
                    JSONObject obj = future.get(5, TimeUnit.SECONDS);
                    System.out.println("yay made it");
                    String s = obj.getString("message");
                    System.out.println("message: " + s);
                } catch (InterruptedException | TimeoutException | ExecutionException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        textview.setText("Response is: " + response.substring(0, 2000));
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textview.setText("That didn't work!");
//            }
//        });

//        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, jsonUrl, null, new Response.Listener<JSONObject>(){
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                textview.setText(jsonObject.toString());
//            }
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                // TODO
//            }
//        });
//
//        imageLoader.get(imageUrl, ImageLoader.getImageListener(imageView, R.drawable.error, R.drawable.error));
//
//
//        // Add the request to the RequestQueue.
////        networkSingleton.addToRequestQueue(stringRequest);
////        networkSingleton.addToRequestQueue(imageRequest);
//        networkSingleton.addToRequestQueue(jsonRequest);
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

//    private class ThreadB extends AsyncTask<Void, Void, JSONObject> {
//        private Context mContext;
//
//        public ThreadB(Context ctx) {
//            mContext = ctx;
//        }
//
//        @Override
//        protected JSONObject doInBackground(Void... params) {
//            System.out.println("threadb running");
//            final RequestFuture<JSONObject> future = RequestFuture.newFuture();
//            RequestQueue queue1 = Volley.newRequestQueue(mContext);
//            String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk";
//            final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), future, future);
//            queue1.add(jsonRequest);
//            try {
//                System.out.println("waiting...");
//                JSONObject response = future.get(10, TimeUnit.SECONDS);
//                System.out.println("made it");
//                String m = response.getString("message");
//                System.out.println("thismsg: " + m);
//                return response;
//            } catch (InterruptedException | ExecutionException | TimeoutException | JSONException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
}
