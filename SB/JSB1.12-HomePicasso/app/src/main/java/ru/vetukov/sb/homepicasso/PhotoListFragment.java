package ru.vetukov.sb.homepicasso;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.valen.homepicasso.R;

import java.util.ArrayList;
import java.util.List;

public class PhotoListFragment extends Fragment implements PhotoListAdapter.OnItemClickListener {

    private static PhotoListFragment instance;

    private RecyclerView recycler;
    List<Photo> photos = new ArrayList<>();

    // Тут использовал синглетон
    public static PhotoListFragment getInstance() {
        if (instance == null) instance = new PhotoListFragment();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);

        recycler =  view.findViewById(R.id.recycler_list);
        recycler.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        getPhotos();
        PhotoListAdapter adapter = new PhotoListAdapter(photos);
        adapter.setOnItemClickListener(this);
        recycler.setAdapter(adapter);

        return view;
    }

    private void getPhotos() {

        // Если не сделать очистку листа, он будет постоянно добавлять ссылки в наш лист...
        photos.clear();

        photos.add(new Photo("https://avatars.mds.yandex.net/get-pdb/51720/f9b1f1cc-e3d8-4781-a5ee-2d1bd229cdb6/s1200?webp=false"));
        photos.add(new Photo("https://im0-tub-ru.yandex.net/i?id=b161a5ded696999f31e1d6f782c8bb34&n=13&exp=1"));
        photos.add(new Photo("https://st.depositphotos.com/1518767/4834/i/950/depositphotos_48345951-stock-photo-blonde-in-straw-hat-smiling.jpg"));
        photos.add(new Photo("https://avatars.mds.yandex.net/get-pdb/69339/8f96838d-1bab-4c3b-8076-09e6ff1e2240/s1200?webp=false"));
        photos.add(new Photo("https://avatars.mds.yandex.net/get-pdb/51720/f9b1f1cc-e3d8-4781-a5ee-2d1bd229cdb6/s1200?webp=false"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/c/c4/NGC253_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/6/68/NGC2276_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/e/e5/NGC2403_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/9/94/NGC2683_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/3/3a/NGC3169_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/a/a8/NGC3344_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/b/b2/NGC4038_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/5/54/NGC4395_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/5/5b/NGC4414_Spiral_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/c/c0/NGC4490_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/7/78/NGC4565_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/8/84/NGC4676_Mice_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/5/53/NGC4910_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/1/19/NGC536_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/9/92/NGC5364_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/a/a0/NGC5426_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/a/a5/NGC5859_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/1/12/NGC5529_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));
        photos.add(new Photo("https://upload.wikimedia.org/wikipedia/commons/2/29/NGC5850_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"));

    }

    @Override
    public void onItemClick(View itemView, int position) {
        Photo photo = photos.get(position);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, PhotoItemFragment.getInstance(photo.getImageSRC()))
                .addToBackStack(null)
                .commit();
    }
}
