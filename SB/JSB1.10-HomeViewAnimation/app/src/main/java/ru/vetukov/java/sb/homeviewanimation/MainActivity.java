package ru.vetukov.java.sb.homeviewanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
                          implements View.OnClickListener {

    private View mVFirst;
    private View mVSecond;

    private Button mBTNXmlAnimation;
    private Button mBTNCodeAnimation;
    private AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVFirst = findViewById(R.id.main_view_first);
        mVSecond = findViewById(R.id.main_view_second);
        mBTNCodeAnimation = findViewById(R.id.main_btn_second);
        mBTNXmlAnimation = findViewById(R.id.main_btn_first);

        mBTNXmlAnimation.setOnClickListener(this);
        mBTNCodeAnimation.setOnClickListener(this);
        mVFirst.setOnClickListener(this);

        //---------------------------
        // CODE

        animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setFillAfter(true);

        final RotateAnimation rotate = new RotateAnimation(0,3600,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(15000);
        animationSet.addAnimation(rotate);

        final TranslateAnimation translate = new TranslateAnimation(
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 250,
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 175
        );
        translate.setDuration(1500);
        animationSet.addAnimation(translate);

        final AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration(1500);
        animationSet.addAnimation(alpha);

        mBTNCodeAnimation.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_first :
                onClickFirstButton();
                break;
            case R.id.main_btn_second :
                onClickSecondButton();
                break;
            case R.id.main_view_first:
                onClickFirstView();
                break;
        }
    }

    private void onClickSecondButton() {
        mVSecond.startAnimation(animationSet);
    }

    private void onClickFirstView() {
        Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
    }

    private void onClickFirstButton() {
        mVFirst.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anima));
    }


}
