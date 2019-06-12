package ru.vetukov.sb.java.homewebviewjs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTvResult = findViewById(R.id.second_txt_result);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            mTvResult.setText(args.getString(MainActivity.STR_KEY));
        } else {
            mTvResult.setText(savedInstanceState.getString(MainActivity.STR_KEY));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MainActivity.STR_KEY, mTvResult.getText().toString());
    }
}
