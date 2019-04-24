package ru.vetukov.java.sb.tutorialpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    public static final String SOME_OPTION_KEY = "SOME_OPTION";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.main_view_setting);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());

        boolean settingValue = settings.getBoolean(SOME_OPTION_KEY, false);

        textView.setText(String.format("Some options: %s", String.valueOf(settingValue)));

    }
}
