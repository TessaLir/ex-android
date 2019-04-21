package ru.vetukov.sb.hemeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView mLoginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginView = findViewById(R.id.login_text);

        if(savedInstanceState == null) {
//            mLoginView.setText(getIntent().getStringExtra(MainActivity.LOGIN_KEY));
        } else {
//            mLoginView.setText(savedInstanceState.getString(MainActivity.LOGIN_KEY));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString(MainActivity.LOGIN_KEY, mLoginView.getText().toString());
    }
}
