package ru.vetukov.sb.hemeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class RundomActivity extends AppCompatActivity {

    public static final String MY_RANDOM_NUMBER = "RandomActivity.MY_RANDOM_NUMBER";

    private Button mButton;
    private TextView mTextView;

    private Random mRandom = new Random();

    private int mRandomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rundom);


        mButton = findViewById(R.id.random_button);
        mTextView = findViewById(R.id.random_text);

        if (savedInstanceState != null) {
            mRandomNumber = savedInstanceState.getInt(MY_RANDOM_NUMBER);
            changeText(mRandomNumber);
        }

        mButton.setOnClickListener(onClick());
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRandomNumber = mRandom.nextInt(150);
                changeText(mRandomNumber);
            }
        };
    }

    private void changeText(int number) {
        mTextView.setText(String.format("%d", number));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(MY_RANDOM_NUMBER, mRandomNumber);
    }
}
