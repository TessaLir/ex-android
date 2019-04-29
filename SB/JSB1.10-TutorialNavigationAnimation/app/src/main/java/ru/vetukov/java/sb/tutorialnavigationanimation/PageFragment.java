package ru.vetukov.java.sb.tutorialnavigationanimation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class PageFragment extends Fragment implements View.OnClickListener {

    public static final String NUMBER_ARGUMENT_KEY = "PageFragment.NUMBER";
    public static final String COLOR_INT = "PageFragment.Color";

    private int mNumber;
    private int mColor;

    private Listener mListener;

    private TextView mTvNumber;
    private Button mBtnNext;
    private Button mBtnPrev;

    public static PageFragment newInstance (final int number) {
        final PageFragment fragment = new PageFragment();
        final Random mRandom = new Random();

        final Bundle arguments = new Bundle();
        arguments.putInt(NUMBER_ARGUMENT_KEY, number);
        arguments.putInt(COLOR_INT, Utils.generateRandomColor(mRandom));
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mNumber = getArguments().getInt(NUMBER_ARGUMENT_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page, container, false);

        mTvNumber = view.findViewById(R.id.fr_page_number);
        mBtnNext = view.findViewById(R.id.fr_page_next);
        mBtnPrev = view.findViewById(R.id.fr_page_prev);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnNext.setOnClickListener(this);
        mBtnPrev.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            mNumber = getArguments().getInt(NUMBER_ARGUMENT_KEY);
            mColor = getArguments().getInt(COLOR_INT);
        } else {
            mNumber = savedInstanceState.getInt(NUMBER_ARGUMENT_KEY);
            mColor = savedInstanceState.getInt(COLOR_INT);
        }

        mTvNumber.setBackgroundColor(mColor);
        mTvNumber.setText(String.valueOf(mNumber));

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COLOR_INT, mColor);
        outState.putInt(NUMBER_ARGUMENT_KEY, mNumber);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (Listener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fr_page_next :
                mListener.onOpenPage(++mNumber);
                break;
            case R.id.fr_page_prev :
                if (mNumber > 0) mNumber--;
                mListener.onPrevPage();
                break;
        }
    }

    public interface Listener {
        void onOpenPage(final int number);
        void onPrevPage();
    }
}
