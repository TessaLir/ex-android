package ru.vetukov.java.sb.tutorialsqlite;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private Cursor mCursor;

    public UserListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(android.R.layout.simple_expandable_list_item_1,
                                                parent,
                                                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        holder.userTextView.setText(mCursor.getString(mCursor.getColumnIndex(ContractUser.COLOMN_NAME_FNAME)));
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public void setmCursor(Cursor cursor) {
        final Cursor oldCursor = mCursor;
        this.mCursor = cursor;
        notifyDataSetChanged();
        if (oldCursor != null) {
            oldCursor.close();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView userTextView;

        public ViewHolder(@NonNull View view) {
            super(view);

            userTextView = view.findViewById(android.R.id.text1);
        }
    }

}
