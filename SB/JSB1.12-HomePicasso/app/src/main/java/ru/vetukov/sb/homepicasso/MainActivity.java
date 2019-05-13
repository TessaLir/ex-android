package ru.vetukov.sb.homepicasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.valen.homepicasso.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_container, PhotoListFragment.getInstance())
                                        .addToBackStack(null)
                                        .commit();

    }
}
