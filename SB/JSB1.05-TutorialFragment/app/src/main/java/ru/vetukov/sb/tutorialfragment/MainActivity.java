package ru.vetukov.sb.tutorialfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity implements  ItemsFragment.Listener {

    private ViewGroup mItemsLlistContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemsLlistContainer = findViewById(R.id.view_items);

        if (savedInstanceState == null) {
            if (isTablet()) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.view_items, new ItemsFragment())
                        .replace(R.id.view_container, DetailFragment.newInstance("N/A"))
                        .commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.view_container, new ItemsFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onItemOneClick(ItemsFragment fragment) {
        final String name = "Item 1";
        createFragment(name);
    }

    @Override
    public void onItemTwoClick(ItemsFragment fragment) {
        final String name = "Item 2";
        createFragment(name);
    }

    private void createFragment(String name) {
//        if (isTablet()) {
//            final DetailFragment detailFragment =
//                    (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.view_container);
//            detailFragment.setName(name);
//        } else {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.view_container, DetailFragment.newInstance(name))
                .commit();
//        }
    }

    private boolean isTablet() {
        return mItemsLlistContainer != null;
    }

//    public void onClick(View view) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(null)
//                .replace(R.id.view_container, new DetailFragment())
//                .commit();
//    }
}
