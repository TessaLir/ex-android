package ru.vetukov.sb.homepicasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.valen.homepicasso.R;

public class MainActivity extends AppCompatActivity {

    // Наша Активити ни знает ни чего и почтини чего не умеет :*(

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // при повороте экрана, фрагменты не перегружаются.
        if (savedInstanceState == null)
            getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_container, PhotoListFragment.getInstance())
                                        .addToBackStack(null)
                                        .commit();

    }
}
