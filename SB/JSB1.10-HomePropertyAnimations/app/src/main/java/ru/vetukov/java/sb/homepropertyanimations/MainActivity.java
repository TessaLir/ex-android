package ru.vetukov.java.sb.homepropertyanimations;

<<<<<<< HEAD
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnFirst;
    private Button mBtnSecond;

    private View mViewFirst;
    private View mViewSecond;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
>>>>>>> mao-10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

        mBtnFirst = findViewById(R.id.main_btn_first);
        mBtnSecond = findViewById(R.id.main_btn_second);

        mViewFirst = findViewById(R.id.main_view_first);
        mViewSecond = findViewById(R.id.main_view_second);

        mBtnFirst.setOnClickListener(this);
        mBtnSecond.setOnClickListener(this);
        mViewFirst.setOnClickListener(this);

        //-----------------------------
        // CODE

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_first :
                animationFirscView();
                break;
            case R.id.main_btn_second :
                animationSecondView();
                break;
            case R.id.main_view_first :
                makeToast("First");
                break;
        }
    }

    // ------------------------------------
    // XML
    private void makeToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void animationFirscView() {
        final AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.anima);
        animatorSet.setTarget(mViewFirst);
        animatorSet.start();
    }
    //-------------------------------------
    // CODE
    private void animationSecondView() {

        final ObjectAnimator rotate = ObjectAnimator.ofFloat(mViewSecond, "rotation", 0, 180);
        rotate.setDuration(500);

        final ObjectAnimator translateX = ObjectAnimator.ofFloat(mViewSecond, "translationX", 0, 100);
        translateX.setDuration(500);

        final ObjectAnimator translateY = ObjectAnimator.ofFloat(mViewSecond, "translationY", 0, 100);
        translateY.setDuration(500);

        final ObjectAnimator alpha = ObjectAnimator.ofFloat(mViewSecond, "alpha", 1, 0);
        alpha.setDuration(500);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotate, translateX, translateY, alpha);
        animatorSet.start();

=======
>>>>>>> mao-10
    }
}
