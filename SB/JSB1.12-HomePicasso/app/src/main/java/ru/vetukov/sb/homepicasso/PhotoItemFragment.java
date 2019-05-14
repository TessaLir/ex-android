package ru.vetukov.sb.homepicasso;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.valen.homepicasso.R;
import com.squareup.picasso.Picasso;

public class PhotoItemFragment extends Fragment {

    public static final String IMAGE_SRC = "PhotoItemFragment.IMAGE_SRC";

    private ImageView mImage;
    private ProgressBar mProgrress;

    public static PhotoItemFragment getInstance(String link) {
        PhotoItemFragment instance = new PhotoItemFragment();
        // КЛадем в Бандл ссылку на нашу картинку, что бы потом ее прогрузить.
        Bundle args = new Bundle();
        args.putString(IMAGE_SRC, link);
        instance.setArguments(args);
        // тут изначально думал именно Синглетоном. но что то передумал, думаю не страшно...
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_info, container, false);
        mImage = view.findViewById(R.id.info_image);

        // Подгружаем полноразмерную картинку.
        Picasso
                .get()
                .load(getArguments().getString(IMAGE_SRC))      // Тут чтение из savedInstanceState не проходило,
                                                                // думал должно работать...
                .into(mImage);

        mImage.setVisibility(View.VISIBLE);

        return view;
    }

}
