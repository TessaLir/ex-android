package ru.vetukov.sb.java.homeconnectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
Урок 1. Определение наличия сети
Отображать статус Connectivity в TextView.

Соотвтственно добавил тут TextView для отображения подключения к сети, так же типа соединения.
Так же добавил кнопку, которая может включать - выключать WiFi на устройстве.

Методы определения сети и Типа сети разделил, что бы 1 метод делал 1 действия, а не два.

Хотел так же добавить кнопку с Телефоном, но так понял это Системный сервис, и у приложения к нему нет доступа...
Либо что то не так пробовал :)
Может позже постигну это

*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity.TAG";
    private static final String MSG_one = "Состояние сети: %s";
    private static final String MSG_two = "Тип сети: %s";

    // Предоставляет API для определения статуса сети
    private ConnectivityManager mConnectivityManager;
    private BroadcastReceiver mConnectivityReceiver = new ConnectivityReceiver();

    // Необходимые TextView для отображения информации
    private TextView mTvResult;
    private TextView mTvResultType;

    // Кнопка смены состояния WiFi
    private Button mBtnSetWiFi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvResult = findViewById(R.id.main_result_connection);
        mTvResultType = findViewById(R.id.main_result_type);

        mBtnSetWiFi = findViewById(R.id.main_btn_wifi);

        mBtnSetWiFi.setOnClickListener(this);

        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        registerReceiver(mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mConnectivityReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.main_btn_wifi :
                changeWiFi();
                break;
        }
    }

    private void logConnectivity(final NetworkInfo networkInfo) {
        mTvResult.setText(getTextConnectivity(networkInfo));
        mTvResultType.setText(getTextType(networkInfo));
    }

    // Смотрим, состояние сети
    private String getTextConnectivity(final NetworkInfo networkInfo) {
        String state;
        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            state = "Подключено";
        } else {
            state = "Нет соединения";
        }
        state = String.format(MSG_one, state);
        return state;
    }

    // Смотрим Тип сети
    private String getTextType(final NetworkInfo networkInfo) {
        String type;

        if (networkInfo != null) {
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    type = "WiFi";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    type = "Мобильный";
                    break;
                default:
                    type = "...Ну не знаю...";
                    break;
            }
        } else {
            type = "Нет соединения!";
        }
        type =  String.format(MSG_two, type);
        return type;
    }

    // Метод сены состояния WiFI
    private void changeWiFi() {
        WifiManager mWiFiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        Log.d(TAG + "!!%$%$", String.format("%d", mWiFiManager.getWifiState()));
        // Тут состояние WiFi сигнала, различный, от "Без соединения" до "Максимальный сигнал"
        if (mWiFiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
            mWiFiManager.setWifiEnabled(true);
        } else {
            mWiFiManager.setWifiEnabled(false);
        }
    }

    private class ConnectivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            logConnectivity(mConnectivityManager.getActiveNetworkInfo());
        }
    }
}
