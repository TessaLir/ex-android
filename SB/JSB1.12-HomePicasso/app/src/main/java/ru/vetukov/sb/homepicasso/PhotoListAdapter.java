package ru.vetukov.sb.homepicasso;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.valen.homepicasso.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Photo> photos;

    public PhotoListAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(mInflater.inflate(R.layout.view_photo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso mPicasso = Picasso.get();
        mPicasso.cancelRequest(holder.image);
        holder.image.setImageBitmap(null);
        mPicasso
                .load(photos.get(position).getImageSRC())
                .resizeDimen(R.dimen.image_size, R.dimen.image_size)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.item_image);
        }
    }

}
