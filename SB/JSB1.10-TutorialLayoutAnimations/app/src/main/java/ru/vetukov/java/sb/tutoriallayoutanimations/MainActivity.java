package ru.vetukov.java.sb.tutoriallayoutanimations;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Random mRandom = new Random();

    private Button mBtnAdd;
    private Button mBtnRemove;

    private ViewGroup mLvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAdd = findViewById(R.id.main_btn_add);
        mBtnRemove = findViewById(R.id.main_btn_remove);
        mLvList = findViewById(R.id.main_view_list);

        mBtnRemove.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.main_btn_add :
                onClickAdd();
                break;
            case R.id.main_btn_remove :
                onClickkRemove();
                break;

        }
    }

    private void onClickAdd() {

        final ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        final int randomColor = Color.argb(
                0xff,
                (int) (mRandom.nextDouble() * 255),
                (int) (mRandom.nextDouble() * 255),
                (int) (mRandom.nextDouble() * 255));

        final View view = new View(MainActivity.this);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(randomColor);
        mLvList.addView(view);

    }

    private void onClickkRemove() {

        final int childCount = mLvList.getChildCount();
        if (childCount > 0) {
            mLvList.removeViewAt((int) (mRandom.nextDouble() * childCount));
        }

    }
}
