package ru.vetukov.java.sb.homegps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String ARRAY_KEY = "MainActivity.ARRAY_KEY";
    private static final int PERMISSION_REQUEST_CODE = 51826;

    private List<Item> mGpsList;
    private LocationManager mLocationManager;

    private RecyclerView mRecycler;
    private GeoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mGpsList = new ArrayList<>();

        if (savedInstanceState != null) {
            //TODO Надо убрать данный костыль на нормальный Parceble, или использовать БД. Зато ратотает ))))
            String line = savedInstanceState.getString(ARRAY_KEY);
            String[] arr = line.split("#");

            for (int i = 0; i < arr.length; i++) {
                String[] ar = arr[i].split(";");
                mGpsList.add(new Item(ar[0], ar[1]));
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            startTrackingLocation();
        } else {
            ActivityCompat.requestPermissions(
                    this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_REQUEST_CODE);
        }

        mRecycler = findViewById(R.id.main_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new GeoListAdapter(this, mGpsList);
        mRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)
                        && grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    throw new RuntimeException("ACCESS_FINE_LOCATION is absolutely required");
                }
            }
        }

        startTrackingLocation();
    }

    @SuppressWarnings("ResourceType")
    private void startTrackingLocation() {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, mLocationListener);
//        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, mLocationListener);

        final Location lastKnownGpsLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastKnownGpsLocation != null) {
            if (mGpsList.size() < 20) {
                printLocation(lastKnownGpsLocation);
            } else {
                mGpsList.remove(0);
                printLocation(lastKnownGpsLocation);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationManager.removeUpdates(mLocationListener);
    }

    private void printLocation(final Location location) {
        if (location != null && mGpsList != null) {

            if (mGpsList.size() >= 10) mGpsList.remove(0);

            mGpsList.add(new Item(String.format("%.04f", location.getLongitude()),
                    String.format("%.04f", location.getLatitude())));
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    //TODO Надо что то с этим сделать, это мне ЖУТКО не нравится. Прямо Омерзительно :)
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            printLocation(location);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
        @Override
        public void onProviderEnabled(String provider) {}
        @Override
        public void onProviderDisabled(String provider) {}
    };


    //TODO Надо убрать данный костыль на нормальный Parceble, или использовать БД. Зато ратотает ))))
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        StringBuilder sb = new StringBuilder();

        for (Item i : mGpsList) {
            sb.append(String.format("%s;%s#", i.getLongetude(), i.getLatitude()));
        }

        outState.putString(ARRAY_KEY, sb.toString());

    }

}
