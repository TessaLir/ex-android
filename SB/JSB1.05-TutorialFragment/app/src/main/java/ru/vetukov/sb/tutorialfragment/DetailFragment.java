package ru.vetukov.sb.tutorialfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    public static final String TAG = "(432423)DetailFragment";
    public static final String NAME_KEY = "DetailFragment.NAME";

    private TextView mNametextView;


    public static DetailFragment newInstance(final String name) {
        final DetailFragment fragment = new DetailFragment();

        final Bundle args = new Bundle();

        args.putString(NAME_KEY, name);
        fragment.setArguments(args);

        return fragment;
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: - " + this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: - " + this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: - " + this);
        View view = inflater.inflate(R.layout.fragment_detail,container, false);

        mNametextView = view.findViewById(R.id.view_name);

        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            mNametextView.setText(getArguments().getString(NAME_KEY));
        } else {
            mNametextView.setText(savedInstanceState.getString(NAME_KEY));
        }


        Log.d(TAG, "onActivityCreated: - " + this);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(NAME_KEY, mNametextView.getText().toString());
    }

    public void setName(final String name) {
        mNametextView.setText(name);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: - " + this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: - " + this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: - " + this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: - " + this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: - " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: - " + this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: - " + this);
    }
}
