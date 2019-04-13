package ru.vetukov.spec.java.level01.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOG_TAG = "##%$#HelloWorld.LOG_TAG";

    private TextView mTVText;
    private CheckBox mCBCheck;
    private Button mBTNCheck;
    private Button mBTNUncheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVText = findViewById(R.id.main_tv_text);
        mCBCheck = findViewById(R.id.main_cb_check);
        mBTNCheck = findViewById(R.id.main_btn_check);
        mBTNUncheck = findViewById(R.id.main_btn_uncheck);

        mBTNCheck.setOnClickListener(this);
        mBTNUncheck.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_check :
                mCBCheck.setChecked(true);
                Log.d(LOG_TAG, "checkBox.setChecked true");
                break;
            case R.id.main_btn_uncheck :
                mCBCheck.setChecked(false);
                Log.d(LOG_TAG, "checkBox.setChecked false");
                break;
        }
    }
}
