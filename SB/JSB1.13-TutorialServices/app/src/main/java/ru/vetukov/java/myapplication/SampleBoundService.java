package ru.vetukov.java.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class SampleBoundService extends Service {

    private final static String TAG = "SampleBoundService";

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {

        SampleBoundService getService() {
            return SampleBoundService.this;
        }
    }

    @Override
    public IBinder onBind(final Intent intent) {
        Log.d(TAG, "onBind");

        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    public Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }
}
