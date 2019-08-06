package ru.vetukov.sb.homefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    public static final String TEXT_NAME = "DetailFragment.TEXT_NAME";

    private TextView mTextView;

    public static DetailFragment getInstance(final String name) {
        final DetailFragment fragment = new DetailFragment();

        final Bundle args = new Bundle();

        args.putString(TEXT_NAME, name);
        fragment.setArguments(args);

        return fragment;
    }

    public void setName(final String name) {
        mTextView.setText(name);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mTextView = view.findViewById(R.id.detail_text);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            mTextView.setText(getArguments().getString(TEXT_NAME));
        } else {
            mTextView.setText(savedInstanceState.getString(TEXT_NAME));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_NAME, mTextView.getText().toString());
    }
}
