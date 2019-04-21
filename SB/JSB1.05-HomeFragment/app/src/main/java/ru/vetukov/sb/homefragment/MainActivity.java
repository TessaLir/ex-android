package ru.vetukov.sb.homefragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int fragmentCount = 0;
    private Button mBtnNextFragment;
    private Button mBtnPrevFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNextFragment = findViewById(R.id.main_btn_next_fragment);
        mBtnPrevFragment = findViewById(R.id.main_btn_back_fragment);

        mBtnNextFragment.setOnClickListener(this);
        mBtnPrevFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_back_fragment :
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() != 0) {
                    fragmentCount--;
                    fm.popBackStack();
                } else {
                    Toast.makeText(MainActivity.this, "Фрагментов тут нет!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.main_btn_next_fragment :
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, DetailFragment.getInstance(String.format("%d", ++fragmentCount)))
                        .commit();
                break;
        }
    }
}
