package ru.vetukov.java.sb.tutorialnavigationanimation;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PageFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_container, PageFragment.newInstance(0))
                                        .commit();
        }
    }


    @Override
    public void onOpenPage(int number) {
        getSupportFragmentManager()
                                    .beginTransaction()
                                    .addToBackStack(null)
                                    .setCustomAnimations(R.anim.in, R.anim.out, R.anim.in, R.anim.out)
                                    .replace(R.id.main_container, PageFragment.newInstance(number))
                                    .commit();
    }

    @Override
    public void onPrevPage() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() !=0) {
            fm.popBackStack();
        } else {
            Toast.makeText(MainActivity.this, "Фрагментов тут нет!", Toast.LENGTH_SHORT).show();
        }

    }
}
