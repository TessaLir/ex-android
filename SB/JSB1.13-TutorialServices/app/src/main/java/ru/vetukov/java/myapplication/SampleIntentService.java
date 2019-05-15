package ru.vetukov.java.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class SampleIntentService extends IntentService {

    public static final String TAG = "SampleIntentService";

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public SampleIntentService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, TAG + ": onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG + ": onDestroy");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(3000);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Intent Service: toast finish", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
