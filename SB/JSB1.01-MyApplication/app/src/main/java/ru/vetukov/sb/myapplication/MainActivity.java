package ru.vetukov.sb.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String OTHER_COUNT = "MainActivity.OTHER_COUNT";

    private final String MSG = "Вы щелкнули на кнопку %s раз.";
    private static int mCountClick = 0;

    private TextView mOutputTextView;
    private Button mWorkButton;
    private Button mClearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        mOutputTextView = findViewById(R.id.main_tvText);
        mWorkButton = findViewById(R.id.main_btnCheck);
        mClearButton = findViewById(R.id.main_btnClear);

        if (savedInstanceState != null) {
            mCountClick = savedInstanceState.getInt(OTHER_COUNT);
            printOutput(mCountClick);
        }

        mWorkButton.setOnClickListener(this);
        mClearButton.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(OTHER_COUNT, mCountClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btnCheck :
                printOutput(++mCountClick);
                break;
            case R.id.main_btnClear :
                printOutput(0);
                break;
        }
    }

    private void printOutput(int i) {
        if (i != 0)
            mOutputTextView.setText(String.format(MSG, i));
        else {
            mCountClick = i;
            mOutputTextView.setText("");
        }
    }

}
