package ru.vetukov.java.sb.tutorialpropertyanimations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnFirst;
    private Button mBtnSecond;

    private View mViewFirst;
    private View mViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnFirst = findViewById(R.id.main_btn_first);
        mBtnSecond = findViewById(R.id.main_btn_second);

        mViewFirst = findViewById(R.id.main_view_first);
        mViewSecond = findViewById(R.id.main_view_second);

        mBtnFirst.setOnClickListener(this);
        mBtnSecond.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_first :

                break;
            case R.id.main_btn_second :

                break;
        }
    }
}
