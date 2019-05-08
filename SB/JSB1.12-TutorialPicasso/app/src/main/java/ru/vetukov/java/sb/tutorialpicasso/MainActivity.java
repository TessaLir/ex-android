package ru.vetukov.java.sb.tutorialpicasso;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private Picasso mPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPicasso = Picasso.get();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.INTERNET }, 8234);
        }

        final RecyclerView list = findViewById(R.id.main_view_list);
        list.setLayoutManager(new GridLayoutManager(this, 2));
        list.setAdapter(new ImageAdapter());

    }


    private class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

        private final LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);

        private final String[] mUrlStrings = {
                "https://upload.wikimedia.org/wikipedia/commons/c/c4/NGC253_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/6/68/NGC2276_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/e/e5/NGC2403_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/9/94/NGC2683_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/3/3a/NGC3169_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/a/a8/NGC3344_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/b/b2/NGC4038_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/5/54/NGC4395_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/5/5b/NGC4414_Spiral_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/c/c0/NGC4490_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/7/78/NGC4565_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/8/84/NGC4676_Mice_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/5/53/NGC4910_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/1/19/NGC536_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/9/92/NGC5364_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/a/a0/NGC5426_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/a/a5/NGC5859_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/1/12/NGC5529_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/2/29/NGC5850_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"
        };


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.view_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            mPicasso.cancelRequest(holder.imageView);
            holder.imageView.setImageBitmap(null);
            mPicasso
                    .load(mUrlStrings[position])
                    .resizeDimen(R.dimen.image_size, R.dimen.image_size)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return mUrlStrings.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final ImageView imageView;

            public ViewHolder(@NonNull View view) {
                super(view);
                imageView = view.findViewById(R.id.item_view_image);
            }
        }

    }
}
