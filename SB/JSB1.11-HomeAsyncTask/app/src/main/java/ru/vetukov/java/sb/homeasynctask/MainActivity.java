package ru.vetukov.java.sb.homeasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String RET_FR_TAG = "MainActivity.RET_FR_TAG";
    public static final String UUID_KEY   = "MainActivity.UUID_KEY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
