package ru.vetukov.java.sb.tutorialdubug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int mFoo;
    private int mBar;
    private int mBaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void doWork() {
        mFoo = 10;
        mBar = 20;
        mBaz = mFoo + mBar;

        Log.d("!!!", "mBaz: " + mBaz);
    }

    public void onTestButtonClick(View view) {
        doWork();
    }
}
