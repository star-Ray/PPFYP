package fypproject.Activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import fypproject.R;
import fypproject.TestCreator;

public class MapActivity extends ActionBarActivity implements OnMapReadyCallback {

    private static final String TAG = "arnono/mapActivity";

    private Gson gson;
    private MapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        gson = new GsonBuilder().setDateFormat(getString(R.string.date_format)).create();

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(NullPointerException e){
            Log.e(TAG, "ActionBar not available. " + e.getMessage());
            e.printStackTrace();
        }

        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_NORMAL);

        mMapFragment = MapFragment.newInstance(options);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mMapFragment);
        fragmentTransaction.commit();

        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        Log.i(TAG, "Map is ready.");
        LatLng location = new LatLng(1.296696, 103.852134);

        String jsonLocation = getIntent().getStringExtra("location");
        if(jsonLocation != null){
            location = gson.fromJson(jsonLocation, LatLng.class);
        }

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
        map.addMarker(new MarkerOptions()
                .position(location)
                .title("New Location"));

        setMarkers(map, TestCreator.getTestLocations());
        setUiSettings(map);
    }

    public void setUiSettings(GoogleMap map){
        UiSettings ui = map.getUiSettings();
        ui.setZoomControlsEnabled(true);
        ui.setCompassEnabled(true);
    }

    public void setMarkers(GoogleMap map, ArrayList<LatLng> list){
        for(int i = 0; i < list.size(); i++){
            map.addMarker(new MarkerOptions().position(list.get(i)).title("Location " + (i+1)));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
