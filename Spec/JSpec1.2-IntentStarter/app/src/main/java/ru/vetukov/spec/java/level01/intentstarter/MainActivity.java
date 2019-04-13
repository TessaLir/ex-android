package ru.vetukov.spec.java.level01.intentstarter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBTNSubmit;
    private EditText mETEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBTNSubmit = findViewById(R.id.main_btn_submit);
        mETEdit = findViewById(R.id.main_et_edit);

        mBTNSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.main_btn_submit) {
            final String text = mETEdit.getText().toString();

            final Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(text)
            );

//            startActivity(intent);

            PackageManager pm = getPackageManager();
            if (intent.resolveActivity(pm) != null) {
                startActivity(intent);
            }

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                //
            }
        }
    }
}
