package ru.vetukov.java.androidfinance.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.vetukov.java.androidfinance.R;
import ru.vetukov.java.androidfinance.app.DBConnection;

public class SplashActivity extends AppCompatActivity {

    private SplashActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        instance = this;

        new Thread() {
            @Override
            public void run() {
                // Загрузка начальных данных (операции, справочники)
                DBConnection.initConnection(instance);

                // имитация загрузки
                imitateLoading();

                //после загрузки переходим на главное окно
                Intent intent = new Intent(instance, MainActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    private void imitateLoading() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
