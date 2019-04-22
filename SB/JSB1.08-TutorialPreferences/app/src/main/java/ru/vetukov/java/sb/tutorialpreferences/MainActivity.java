package ru.vetukov.java.sb.tutorialpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        /*implements CompoundButton.OnCheckedChangeListener, SharedPreferences.OnSharedPreferenceChangeListener*/
{


/*    public static final String SETTING_KEY = "MainActivity.SETTING_KEY";

    private SharedPreferences mSettings;

    private TextView mTVSetting;
    private CheckBox mCBCheck;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view_container, new InfoFragment())
                .commit();

/*        mTVSetting = findViewById(R.id.main_view_setting);
        mCBCheck = findViewById(R.id.main_cb_check);

        mSettings = getSharedPreferences("settings", 0);
        mSettings.registerOnSharedPreferenceChangeListener(this);

        updateSettingsText();

        mCBCheck.setChecked(mSettings.getBoolean(SETTING_KEY, false));
        mCBCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSettings.edit().putBoolean(SETTING_KEY, isChecked).apply();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_settings :
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_view_container, new SettingFragment())
                        .commit();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(SETTING_KEY)) {
            updateSettingsText();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    private void updateSettingsText() {
        String settint = String.format("Some option: %s", mSettings.getBoolean(SETTING_KEY, false));
        mTVSetting.setText(settint);
    }*/
}
