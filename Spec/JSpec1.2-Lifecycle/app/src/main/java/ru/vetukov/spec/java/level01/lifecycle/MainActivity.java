package ru.vetukov.spec.java.level01.lifecycle;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQ_CODE = 25431;
    public static final String KEY_HAPPY = "MainActivity.KEY_HAPPY";

    private TextView mTVText;
    private Button mBTNNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVText = findViewById(R.id.main_tv_text);
        mBTNNext = findViewById(R.id.main_btn_next);

        mBTNNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(this, OtherActivity.class);
        intent.putExtra(KEY_HAPPY, "hello~!");
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQ_CODE) {
            if (data.hasExtra(KEY_HAPPY)) {
                final String result = data.getStringExtra(KEY_HAPPY);
                mTVText.setText(String.format("Result: %s", result));
            }
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
