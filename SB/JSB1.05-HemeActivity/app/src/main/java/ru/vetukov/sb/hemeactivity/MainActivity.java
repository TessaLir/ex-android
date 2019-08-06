package ru.vetukov.sb.hemeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

//    public static final String LOGIN_KEY = "MainActivity.LOGIN_KEY";
//
//    private Button mBtnCheckLogin;
//    private EditText mETLogin;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mBtnCheckLogin = findViewById(R.id.main_btn_check_login);
//        mETLogin = findViewById(R.id.main_et_Login);
//
//        mBtnCheckLogin.setOnClickListener(onClickCheckLogin());

        mButton = findViewById(R.id.main_btnToNext);

        mButton.setOnClickListener(onClick());

    }

//    private View.OnClickListener onClickCheckLogin() {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                intent.putExtra(LOGIN_KEY, mETLogin.getText().toString());
//                startActivity(intent);
//            }
//        };
//    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RundomActivity.class));
            }
        };
    }
}
