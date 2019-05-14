package ru.vetukov.sb.homepicasso;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.valen.homepicasso.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.ResourceBundle;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Photo> photos;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    private OnItemClickListener clickListener;

    void setOnItemClickListener(OnItemClickListener listener)
    {
        this.clickListener = listener;
    }

    public PhotoListAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(mInflater.inflate(R.layout.view_photo_item, parent,false), clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Picasso mPicasso = Picasso.get();
        mPicasso.cancelRequest(holder.image);
        holder.image.setImageBitmap(null);
        mPicasso
                .load(photos.get(position).getImageSRC())
                .placeholder(R.drawable.thecat)
                .resizeDimen(R.dimen.image_size, R.dimen.image_size)
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private OnItemClickListener clickListener;

        ImageView image;
        ProgressBar progressBar;

        ViewHolder(@NonNull View view, OnItemClickListener listener) {
            super(view);
            image = view.findViewById(R.id.item_image);
            progressBar = view.findViewById(R.id.item_progress);
            clickListener = listener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            clickListener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

}
