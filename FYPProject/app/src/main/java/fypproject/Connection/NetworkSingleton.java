package fypproject.Connection;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class NetworkSingleton {
    private static NetworkSingleton networkInstance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context mContext;

    private NetworkSingleton(Context context) {
        mContext = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
        System.out.println("NetworkSingleton: Created.");
    }

    public static synchronized NetworkSingleton getInstance(Context context) {
        if (networkInstance == null) {
            networkInstance = new NetworkSingleton(context);
        }
        System.out.println("NetworkSingleton: Instance called.");
        return networkInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        System.out.println("NetworkSingleton: RequestQueue is called.");
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
        System.out.println("NetworkSingleton: New request " + request + "  is added to requestQueue!");
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}