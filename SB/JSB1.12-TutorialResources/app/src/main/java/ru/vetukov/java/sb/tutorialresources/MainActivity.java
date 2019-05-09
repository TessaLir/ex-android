package ru.vetukov.java.sb.tutorialresources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item:
                Log.d("!@#", "Item");
                break;

            case R.id.menu_hidden_item:
                Log.d("!@#", "Hidden item");
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
