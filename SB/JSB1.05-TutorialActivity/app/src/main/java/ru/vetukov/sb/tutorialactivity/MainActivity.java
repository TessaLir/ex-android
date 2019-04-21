package ru.vetukov.sb.tutorialactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "(&^$%%$)MainActivity";

    private static final String RANDOM_NUMBER_KEY = "MainActivity.RANDOM_NUMBER_KEY";
    private static final String USER_NUMBR_KEY = "MainActivity.UESER_NUMBER";

    private final Random mRandom = new Random();

    private Button mButton;
    private EditText mEditText;
    private TextView mTextView;

    private int mRandomNumber;
    private int mUserNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.btnTest);
        mEditText = findViewById(R.id.main_user_number);
        mTextView = findViewById(R.id.main_sum);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRandomNumber = mRandom.nextInt(100);
                mUserNumber = Integer.parseInt(mEditText.getText().toString());
                mTextView.setText(String.format("%d", mRandomNumber + mUserNumber));
            }
        });

        if(savedInstanceState != null) {
            mRandomNumber = savedInstanceState.getInt(RANDOM_NUMBER_KEY);
            mUserNumber = savedInstanceState.getInt(USER_NUMBR_KEY);

            mTextView.setText(String.format("%d", mRandomNumber + mUserNumber));
        }

        Log.d(TAG, "onCreate: " + this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(RANDOM_NUMBER_KEY, mRandomNumber);
        outState.putInt(USER_NUMBR_KEY, mUserNumber);
    }


    public void onButtonClick(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:   - " + this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onRestart:   - " + this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + this);
    }
}
