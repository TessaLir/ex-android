package ru.vetukov.sb.java.tutorialconnectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.TAG";

    // Предоставляет API для определения статуса сети
    private ConnectivityManager mConnectivityManager;
    private BroadcastReceiver mConnectivityReceiver = new ConnectivityReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        registerReceiver(mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mConnectivityReceiver);
    }

    private void logConnectivity(final NetworkInfo networkInfo) {
        final String state;
        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            state = "Connected";
        } else {
            state = "Not connected";
        }
        Log.d(TAG + "%$###", "Network: " + state);
    }

    private class ConnectivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            logConnectivity(mConnectivityManager.getActiveNetworkInfo());
        }
    }
}
