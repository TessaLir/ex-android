package ru.vetukov.java.homeservices;

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
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity
                            extends AppCompatActivity
                            implements View.OnClickListener {

    public static final String RESULT_STRING    = "Result: %d";
    public static final String RESULT_KEY       = "MainActivity.RESULT_KEY";

    private CalcBoundService mService;
    private boolean mIsServiceBound;

    private Button mBtnAdd;
    private Button mBtnMin;
    private Button mBtnMult;
    private Button mBtnDev;

    private EditText mEtFirst;
    private EditText mEtSecond;

    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAdd     = findViewById(R.id.main_btn_sum);
        mBtnMin     = findViewById(R.id.main_btn_min);
        mBtnMult    = findViewById(R.id.main_btn_mult);
        mBtnDev     = findViewById(R.id.main_btn_dev);

        mEtFirst    = findViewById(R.id.main_et_one);
        mEtSecond   = findViewById(R.id.main_et_two);

        mTvResult   = findViewById(R.id.main_tv_result);

        mBtnAdd.setOnClickListener(this);
        mBtnMin.setOnClickListener(this);
        mBtnMult.setOnClickListener(this);
        mBtnDev.setOnClickListener(this);

        if (savedInstanceState != null) {
            mTvResult.setText(savedInstanceState.getString(RESULT_KEY));
        }
    }

    @Override
    public void onClick(View v) {
        if (mIsServiceBound) {
            final int x = Integer.parseInt(mEtFirst.getText().toString());
            final int y = Integer.parseInt(mEtSecond.getText().toString());
            String result = "";
            switch (v.getId()) {
                case R.id.main_btn_sum:
                    result = String.format(RESULT_STRING,mService.getSumma(x, y));
                    break;
                case R.id.main_btn_min:
                    result = String.format(RESULT_STRING,mService.getMinus(x, y));
                    break;
                case R.id.main_btn_mult:
                    result = String.format(RESULT_STRING,mService.getMulty(x, y));
                    break;
                case R.id.main_btn_dev:
                    // прошу тут меня сильно не судить :) получаем целочисленую переменную :)
                    result = String.format(RESULT_STRING,mService.getDevision(x, y));
                    break;
            }
            mTvResult.setText(result);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, CalcBoundService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mIsServiceBound) {
            unbindService(mConnection);
            mIsServiceBound = false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESULT_KEY, mTvResult.getText().toString());
    }

    private final ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(final ComponentName name, final IBinder service) {
            final CalcBoundService.LocalBinder binder = (CalcBoundService.LocalBinder) service;
            mService = binder.getService();
            mIsServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(final ComponentName name) {
            mIsServiceBound = false;
        }
    };
}
