package ru.vetukov.java.sb.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_conteiner, PhotoFragment.getInstance());
        }

    }
}
