package ru.vetukov.java.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final SimpleDateFormat mDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);

    private SampleBoundService mService;
    private boolean mIsServiceBound;

    private Button mBtnStarted;
    private Button mBtnIntent;
    private Button mBtnBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStarted = findViewById(R.id.main_btn_started_service);
        mBtnIntent = findViewById(R.id.main_btn_intent_service);
        mBtnBound = findViewById(R.id.main_btn_bound_service);

        mBtnStarted.setOnClickListener(this);
        mBtnIntent.setOnClickListener(this);
        mBtnBound.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_started_service :
                onStartedServiceButtonClick(view);
                break;
            case R.id.main_btn_intent_service :
                onIntentServiceButtonClick(view);
                break;
            case R.id.main_btn_bound_service :
                onBoundServiceButtonClick(view);
                break;
        }
    }

    // 1.
    private void onStartedServiceButtonClick(View view) {
        startService(new Intent(this, SampleStartedService.class));
    }

    // 2.
    private void onIntentServiceButtonClick(View view) {
        startService(new Intent(this, SampleIntentService.class));
    }

    // 3.
    private void onBoundServiceButtonClick(View view) {
        if (mIsServiceBound) {
            Toast.makeText(this, mDateFormat.format(mService.getCurrentTime()), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        bindService(new Intent(this, SampleBoundService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mIsServiceBound) {
            unbindService(mConnection);
            mIsServiceBound = false;
        }
    }

    private final ServiceConnection mConnection = new ServiceConnection() {

        private static final String TAG = "SampleBoundService";

        @Override
        public void onServiceConnected(final ComponentName name, final IBinder service) {
            Log.d(TAG, "onServiceConnected");

            final SampleBoundService.LocalBinder binder = (SampleBoundService.LocalBinder) service;
            mService = binder.getService();
            mIsServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(final ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");

            mIsServiceBound = false;
        }
    };

}
