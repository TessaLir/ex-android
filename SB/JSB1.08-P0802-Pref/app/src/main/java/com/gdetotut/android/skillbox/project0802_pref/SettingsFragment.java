package com.gdetotut.android.skillbox.project0802_pref;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SettingsFragment extends PreferenceFragmentCompat {

    public static final String TAG = "SettingsFragment";

    @Override
    public void onResume() {
        super.onResume();
        setupActionBar(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        setupActionBar(false);
    }

    private void setupActionBar(boolean show) {
        final MainActivity a = (MainActivity) getActivity();
        final ActionBar actionBar = a!= null ? a.getSupportActionBar() : null;
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(show);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(inflater.getContext());
        final String value = sharedPreferences.getString(getString(R.string.pref_list_key), getString(R.string.pref_list_default));
        final Preference listPref = findPreference(getResources().getString(R.string.pref_list_key));
        listPref.setSummary(getResources().getString(R.string.pref_list_summary, value));

        listPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                preference.setSummary(getResources().getString(R.string.pref_list_summary, o.toString()));
                return true;
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * Рекомендовано в документации делать так.
     */
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.preferences, s);
    }

}
