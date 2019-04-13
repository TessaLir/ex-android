package ru.vetukov.spec.java.level01.lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class OtherActivity extends AppCompatActivity {

    private EditText mETEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        StringBuilder hello = new StringBuilder();

        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.KEY_HAPPY)) {
            hello.append(intent.getStringExtra(MainActivity.KEY_HAPPY));
        }

        mETEdit = findViewById(R.id.other_et_edit);

        mETEdit.setText(hello);

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();

        String result = mETEdit.getText().toString();
        intent.putExtra(MainActivity.KEY_HAPPY, result);

        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("happy", "SecondActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("happy", "SecondActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("happy", "SecondActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("happy", "SecondActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("happy", "SecondActivity onDestroy");
    }

}
