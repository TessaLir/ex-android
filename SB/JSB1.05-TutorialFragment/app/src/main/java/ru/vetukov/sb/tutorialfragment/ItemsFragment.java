package ru.vetukov.sb.tutorialfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ItemsFragment extends Fragment {

    private Listener mListener;

    private Button mButtonOne;
    private Button mButtonTwo;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (Listener) getActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButtonOne = view.findViewById(R.id.button_view_iten_01);
        mButtonTwo = view.findViewById(R.id.button_view_iten_02);

        mButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemOneClick(ItemsFragment.this);
            }
        });

        mButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemTwoClick(ItemsFragment.this);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items, container,false);
    }

    public interface Listener {
        void onItemOneClick(ItemsFragment fragment);
        void onItemTwoClick(ItemsFragment fragment);
    }
}
