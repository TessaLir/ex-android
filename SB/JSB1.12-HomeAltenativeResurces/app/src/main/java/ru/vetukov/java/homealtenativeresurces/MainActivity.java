package ru.vetukov.java.homealtenativeresurces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getBoolean(R.bool.phone)) {
            Log.d(TAG, "Device is phone");
        } else {
            Log.d(TAG, "Device is tablet");
        }
    }
}
