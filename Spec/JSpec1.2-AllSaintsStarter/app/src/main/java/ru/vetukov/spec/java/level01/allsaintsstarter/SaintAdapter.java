package ru.vetukov.spec.java.level01.allsaintsstarter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class SaintAdapter extends ArrayAdapter {

    private List<Saint> saints;

    public SaintAdapter(@NonNull Context context, int resource, List<Saint> saints) {
        super(context, resource);
        this.saints = saints;
    }

    @Override
    public int getCount() {
        return saints.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.listviewitem, parent, false);

        Saint saint = saints.get(position);

        TextView name = view.findViewById(R.id.item_text);
        TextView dob = view.findViewById(R.id.item_dob);
        TextView dod = view.findViewById(R.id.item_dod);
        RatingBar rating = view.findViewById(R.id.item_rating);

        name.setText(saint.getmName());
        dob.setText(saint.getmDob());
        dod.setText(saint.getmDod());
        rating.setRating((saint.getmRating()));

        return view;
    }
}
