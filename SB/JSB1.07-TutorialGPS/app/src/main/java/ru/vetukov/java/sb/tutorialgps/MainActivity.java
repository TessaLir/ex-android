package ru.vetukov.java.sb.tutorialgps;

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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1234;

    private LocationManager mLocationManaget;
    private TextView mOutput;

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            printLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationManaget = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mOutput = findViewById(R.id.view_output);

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
        mLocationManaget.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        mLocationManaget.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);

        final Location lastKnownGpsLocation = mLocationManaget.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastKnownGpsLocation != null) {
            printLocation(lastKnownGpsLocation);
        } else {
            final Location lastKnownNetworkLocation = mLocationManaget.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            printLocation(lastKnownNetworkLocation);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLocationManaget.removeUpdates(mLocationListener);
    }

    private void printLocation(final Location location) {
        if (location != null) {
            mOutput.setText(String.format("Latitude: %f; longitude: %f", location.getLatitude(), location.getLongitude()));
        } else {
            mOutput.setText("Location n/a");
        }
    }
}
