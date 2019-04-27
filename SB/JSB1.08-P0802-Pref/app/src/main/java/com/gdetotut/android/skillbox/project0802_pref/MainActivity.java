//
// Модуль 8, урок 2: Shared Preferences
//          Добавить еще пункт в настройки
//
// 35987: Валерий Куликов, 79068017667@yandex.ru
// 2018/11/03
//
//  Примечания:
// 		- на эмуляторе с API-16 меню никак не хотело отображаться, пока не использовал support.*.Toolbar
// 		- обновление виджетов тулбара при смене фрагментов (кнопка Home, само меню)
// 		- закрытие приложения вместе с удалением последнего фрагмента из стека
//

package com.gdetotut.android.skillbox.project0802_pref;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private MenuItem mSettingsMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.view_container, new InfoFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mSettingsMenuItem = menu.findItem(R.id.action_settings);
        updateMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.view_container, new SettingsFragment(), SettingsFragment.TAG)
                        .addToBackStack(SettingsFragment.TAG)
                        .commit();
                getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        // Так приходится делать, чтобы спрятать меню при запуске SettingsFragment.
                        // Этого могло не случиться, используй мы отдельную активность для [SharedPreference]
                        updateMenu();
                    }
                });
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getFragments().size() == 0) {
            finish();
        }
        updateMenu();
    }

    private void updateMenu() {
        mSettingsMenuItem.setVisible(getSupportFragmentManager().findFragmentByTag(SettingsFragment.TAG) == null);
    }
}
