package com.gdetotut.android.skillbox.project0802_pref;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InfoFragment extends Fragment {

    public static final String TAG = "InfoFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_info, container, false);
        final TextView textViewCheckOption = view.findViewById(R.id.view_check_option);
        final TextView textViewListRole = view.findViewById(R.id.view_list_role);

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(inflater.getContext());

        final boolean checkValue = settings.getBoolean(getResources().getString(R.string.pref_checkbox_key), false);
        textViewCheckOption.setText(String.format(getString(R.string.fmt_some_option), String.valueOf(checkValue)));

        final String listValue = settings.getString(getString(R.string.pref_list_key), getString(R.string.pref_list_not_choosen));
        textViewListRole.setText(String.format(getString(R.string.fmt_role), String.valueOf(listValue)));

        return view;
    }

}
