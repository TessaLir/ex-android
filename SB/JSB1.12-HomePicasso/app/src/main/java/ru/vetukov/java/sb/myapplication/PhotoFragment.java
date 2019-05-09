package ru.vetukov.java.sb.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

public class PhotoFragment extends Fragment {

    private RecyclerView.Recycler mRecycler;

    public static PhotoFragment getInstance() {
        return new PhotoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_photo_grid, container, false);

        mRecycler = view.findViewById(R.id.content_recycler);

        return view;
    }
}
