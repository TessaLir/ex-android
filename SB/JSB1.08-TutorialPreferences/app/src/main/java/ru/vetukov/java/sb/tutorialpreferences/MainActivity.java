package ru.vetukov.java.sb.tutorialpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String SETTING_KEY = "MainActivity.SETTING_KEY";

    private SharedPreferences mSettings;

    private TextView mTVSetting;
    private CheckBox mCBCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVSetting = findViewById(R.id.main_view_setting);
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
        });

    }

    @Override
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
    }
}
