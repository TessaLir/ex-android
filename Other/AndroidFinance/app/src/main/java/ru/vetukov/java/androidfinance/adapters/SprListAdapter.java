package ru.vetukov.java.androidfinance.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.vetukov.java.androidfinance.R;
//import ru.vetukov.java.androidfinance.fragments.SprListFragment.OnListFragmentInteractionListener;
import ru.vetukov.java.core.interfaces.TreeNode;

import java.util.List;

public class SprListAdapter extends RecyclerView.Adapter<SprListAdapter.ViewHolder> {

    private final List<? extends TreeNode> mValues;
//    private final OnListFragmentInteractionListener mListener;

    public SprListAdapter(List<? extends TreeNode> items/*, OnListFragmentInteractionListener listener*/) {
        mValues = items;
//        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spr_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTVTitle.setText(mValues.get(position).getName());

/*        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTVTitle;

        public ViewHolder(View view) {
            super(view);
            mTVTitle = view.findViewById(R.id.spr_item_title);

        }

        @Override
        public String toString() {
            return mTVTitle.getText().toString();
        }
    }
}
