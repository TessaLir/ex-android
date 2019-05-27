package ru.vetukov.sb.java.homeconnectivity_v02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.vetukov.sb.java.homeconnectivity_v02.databinding.ActivityMainBinding;

/*
Урок 1. Определение наличия сети
Отображать статус Connectivity в TextView.

Дубль два.
Переделываю стандартный вариант на реализацию проекта с испоьзованием DataBinding.
В предыдущий проект вставить не вышло, потратил пару часов на попытку там все поднять, но увы и ах
на всеже добавленных проектах DataBinding себя ведет более адекватно и менее раздражительно.
приступим.

*/

public class MainActivity extends AppCompatActivity {

    private static final String MSG_one = "Состояние сети: %s";
    private static final String MSG_two = "Тип сети: %s";

    // Предоставляет API для определения статуса сети
    private ConnectivityManager mConnectivityManager;
    private BroadcastReceiver mConnectivityReceiver = new ConnectivityReceiver();
    private NetworkInfo networkInfo;

    private ActivityMainBinding data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Connection conn = getConnection("NO", "NO");
        data.setConnection(conn);

        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        registerReceiver(mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    private Connection getConnection(String one, String two) {
        return new Connection(String.format(MSG_one, one), String.format(MSG_two,two));
    }

    private class ConnectivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            networkInfo = mConnectivityManager.getActiveNetworkInfo();
            Connection conn = new Connection(getStatus(), getType());
            data.setConnection(conn);
        }
    }

    private String getStatus() {
        String state;

        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            state = "Подключено";
        } else {
            state = "Нет соединения";
        }
        state = String.format(MSG_one, state);
        return state;
    }

    private String getType() {
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

}
