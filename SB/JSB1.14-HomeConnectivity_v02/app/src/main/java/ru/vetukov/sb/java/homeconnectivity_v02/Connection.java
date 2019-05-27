package ru.vetukov.sb.java.homeconnectivity_v02;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Connection {

    private String isConnected;
    private String type;

    public Connection(String isConnected, String type) {
        this.isConnected = isConnected;
        this.type = type;
    }

    public String getIsConnected() {
        return isConnected;
    }

    public String getType() {
        return type;
    }

    public void wifiChange(View view) {
        Context context = view.getContext();
        WifiManager mWiFiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (mWiFiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
            mWiFiManager.setWifiEnabled(true);
        } else {
            mWiFiManager.setWifiEnabled(false);
        }
    }
}
