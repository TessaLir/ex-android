package ru.vetukov.java.sb.homegpsplayservices;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final String ARRAY_KEY = "MainActivity.ARRAY_KEY";
    private static final int PERMISSION_REQUEST_CODE = 23426;

    private GoogleApiClient mGoogleApiClient;
    private GeoListAdapter mAdapter;
    private List<Item> mGeoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGeoList = new ArrayList<>();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        if (savedInstanceState != null) {
            //TODO Надо убрать данный костыль на нормальный Parceble, или использовать БД. Зато ратотает ))))
            String line = savedInstanceState.getString(ARRAY_KEY);
            String[] arr = line.split("#");

            for (int i = 0; i < arr.length; i++) {
                String[] ar = arr[i].split(";");
                mGeoList.add(new Item(ar[0], ar[1]));
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            startTrackingLocation();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                    PERMISSION_REQUEST_CODE
            );
        }

        RecyclerView mRecycler = findViewById(R.id.main_recycler);
        mAdapter = new GeoListAdapter(this, mGeoList);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);

    }

    private void startTrackingLocation() {
        mGoogleApiClient.connect();
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

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
//        printLocation(LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient));

        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient,
                locationRequest,
                this
        );
    }

    @Override

    public void onLocationChanged(Location location) {
        printLocation(location);
    }

    private void printLocation(final Location location) {
        if (location != null && mGeoList != null) {

            if (mGeoList.size() >= 10) mGeoList.remove(0);

            mGeoList.add(new Item(String.format("%.04f", location.getLongitude()),
                    String.format("%.04f", location.getLatitude())));
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }
    //TODO Надо убрать данный костыль на нормальный Parceble, или использовать БД. Зато ратотает ))))

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mGeoList.size(); i++) {
            sb.append(String.format("%s;%s#", mGeoList.get(i).getLongetude(), mGeoList.get(i).getLatitude()));
        }

        outState.putString(ARRAY_KEY, sb.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //TODO на будущее
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        throw new RuntimeException("Connection failed");
    }

}
