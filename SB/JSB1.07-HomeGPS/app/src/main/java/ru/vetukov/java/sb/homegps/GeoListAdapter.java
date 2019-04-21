package ru.vetukov.java.sb.homegps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GeoListAdapter extends RecyclerView.Adapter<GeoListAdapter.ViewHolder> {

    private List<Item> mGeoList;
    private LayoutInflater mInflater;

    public GeoListAdapter(Context context, List<Item> mGeoList) {
        this.mGeoList = mGeoList;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.geo_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mLatitude.setText(mGeoList.get(position).getLatitude());
        holder.mLongitude.setText(mGeoList.get(position).getLongetude());
    }

    @Override
    public int getItemCount() {
        return mGeoList == null ? 0 : mGeoList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView mLongitude;
        TextView mLatitude;

        public ViewHolder(@NonNull View i) {
            super(i);

            mLongitude = i.findViewById(R.id.item_value_longitude);
            mLatitude = i.findViewById(R.id.item_value_latitude);
        }
    }

}
