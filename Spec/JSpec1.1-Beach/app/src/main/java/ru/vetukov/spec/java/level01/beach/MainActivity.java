package ru.vetukov.spec.java.level01.beach;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buyTrip(View view) {
        Snackbar.make(findViewById(R.id.main_coordinator),
                        "Ваше путешествие заказано",
                        Snackbar.LENGTH_SHORT).show();

    }
}
