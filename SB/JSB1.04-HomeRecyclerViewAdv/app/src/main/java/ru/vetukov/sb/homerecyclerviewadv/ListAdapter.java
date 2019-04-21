package ru.vetukov.sb.homerecyclerviewadv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Item> items;
    private LayoutInflater mInflater;

    public ListAdapter(Context context, List<Item> items) {
        mInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText(items.get(position).getmLogo());
        holder.mImageLogo.setBackgroundColor(items.get(position).getmLogoColor());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageLogo;
        TextView mTitle;

        public ViewHolder(@NonNull View i) {
            super(i);

            mImageLogo = i.findViewById(R.id.item_image_color);
            mTitle = i.findViewById(R.id.item_title);
        }
    }
}
