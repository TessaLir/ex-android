package com.gdetotut.skillbox.project0803sqlite;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private final LayoutInflater mInflater;

    private Cursor mCursor;

    ArticleListAdapter(@NonNull Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflater.inflate(R.layout.view_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        mCursor.moveToPosition(i);
        viewHolder.titleTextView.setText(mCursor.getString(mCursor.getColumnIndex(Article.COLUMN_TITLE)));
        viewHolder.textTextView.setText(mCursor.getString(mCursor.getColumnIndex(Article.COLUMN_TEXT)));
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    void setCursor(final Cursor cursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = cursor;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView titleTextView;
        final TextView textTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.view_title);
            textTextView = itemView.findViewById(R.id.view_text);
        }
    }

}
