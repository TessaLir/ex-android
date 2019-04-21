package ru.vetukov.sb.tutorialactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "(&^$%%$)MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "onCreate:   - " + this);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:   - " + this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onRestart:   - " + this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + this);
    }

    public void onButtonClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
