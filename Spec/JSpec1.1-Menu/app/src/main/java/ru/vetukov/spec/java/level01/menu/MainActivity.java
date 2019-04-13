package ru.vetukov.spec.java.level01.menu;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

//        MenuItem item = menu.add(Menu.NONE, 444, Menu.NONE, "Battary");
//        item.setIcon(R.drawable.battary);
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_add :
                Toast.makeText(this, "Add", Toast.LENGTH_LONG).show();
                break;
            case R.id.main_battary :
                Toast.makeText(this, "Battary", Toast.LENGTH_LONG).show();
                break;
            case R.id.main_train :
                Toast.makeText(this, "Train", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
