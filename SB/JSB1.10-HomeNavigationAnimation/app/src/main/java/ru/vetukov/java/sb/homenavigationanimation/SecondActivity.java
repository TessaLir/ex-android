package ru.vetukov.java.sb.homenavigationanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NUMBER_EXTRA_KEY = "SecondActivity.NUMBER_EXTRA_KEY";

    private final Random mRandom = new Random();

    private int mNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Intent intent = getIntent();
        if (intent != null) {
            mNumber = intent.getIntExtra(NUMBER_EXTRA_KEY, 0);
        }

        findViewById(R.id.second_view_number).setBackgroundColor(Utils.generateRandomColor(mRandom));
        ((TextView) findViewById(R.id.second_view_number)).setText(String.valueOf(mNumber));
        findViewById(R.id.second_btn_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.second_btn_next :
                onClickNextBtn();
                break;
        }
    }

    private void onClickNextBtn() {
        final Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
        intent.putExtra(NUMBER_EXTRA_KEY, ++mNumber);
        startActivity(intent);
        overridePendingTransition(R.anim.in, R.anim.out);
    }
}
