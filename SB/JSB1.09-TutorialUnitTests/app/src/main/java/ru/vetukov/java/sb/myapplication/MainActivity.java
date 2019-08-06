package ru.vetukov.java.sb.myapplication;

import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final SimpleCalculator mSimpleCalculator = new SimpleCalculator();

    private TextView mTvResult;

    private Button mBtnAdd;
    private Button mBtnSub;

    private EditText mEtFirstArgument;
    private EditText mEtSecondArgument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvResult = findViewById(R.id.main_tv_result);
        mEtFirstArgument = findViewById(R.id.main_et_firsr_arg);
        mEtSecondArgument = findViewById(R.id.main_et_second_arg);

        mBtnAdd = findViewById(R.id.main_btn_add);
        mBtnSub = findViewById(R.id.main_btn_sub);

    }

    public void onClick(View view) {
        int a = Integer.parseInt(mEtFirstArgument.getText().toString());
        int b = Integer.parseInt(mEtSecondArgument.getText().toString());
        switch (view.getId()) {
            case R.id.main_btn_add :
                printResult(mSimpleCalculator.add(a,b));
                break;
            case R.id.main_btn_sub :
                printResult(mSimpleCalculator.sub(a,b));
                break;
            case R.id.main_btn_mult :
                printResult(mSimpleCalculator.mult(a,b));
                break;
            case R.id.main_btn_div :
                printResult(mSimpleCalculator.div(a,b));
                break;
        }
    }

    private void printResult(int result) {
        mTvResult.setText(String.format("%s", result));
    }
}
