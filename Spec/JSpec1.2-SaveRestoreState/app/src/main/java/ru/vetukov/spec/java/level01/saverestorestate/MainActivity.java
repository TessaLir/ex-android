package ru.vetukov.spec.java.level01.saverestorestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String COUNT_CLICK = "MainActivity.COUNT_CLICK";

    private static final String MSG = "%s кликов по CheckBox.";
    private static int mCountClick = 0;

    private TextView mTVText;
    private CheckBox mCBCheckOne;
    private CheckBox mCBCheckTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCountClick = savedInstanceState.getInt(COUNT_CLICK);
        }

        mTVText = findViewById(R.id.main_tv_text);
        mCBCheckOne = findViewById(R.id.main_cb_check_one);
        mCBCheckTwo = findViewById(R.id.main_cb_check_two);

        setText(mCountClick);

        mCBCheckOne.setOnClickListener(this);
        mCBCheckTwo.setOnClickListener(this);

    }

    private void setText(int number) {
        mTVText.setText(String.format(MSG, number));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_cb_check_one :
                setText(++mCountClick);
                break;
            case R.id.main_cb_check_two :
                setText(++mCountClick);
                break;

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COUNT_CLICK, mCountClick);
    }
}
