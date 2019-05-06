package ru.vetukov.java.sb.homethreads;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvText = findViewById(R.id.main_txt_text);
    }

    public void onClick(View view) {

        Log.d("!!!", "Main thread: " + Thread.currentThread().getName());
        Thread th = new Thread(new myRunnable());
        th.start();

    }

    private class myRunnable implements Runnable {

        @Override
        public void run() {
            Log.d("!!!", "New thread: " + Thread.currentThread().getName());
            mHandler.post(() -> mTvText.setText("Здасте")) ;
        }
        
    }

}
