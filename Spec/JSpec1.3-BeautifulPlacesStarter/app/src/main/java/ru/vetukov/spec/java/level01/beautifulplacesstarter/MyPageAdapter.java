package ru.vetukov.spec.java.level01.beautifulplacesstarter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MyPageAdapter extends PagerAdapter {

    private List<Place> mPlace;

    public MyPageAdapter(List<Place> mPlace) {
        this.mPlace = mPlace;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Context context = container.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View layout = inflater.inflate(R.layout.page, container, false);

        Place place = mPlace.get(position);
        String pictureURL = place.getmPicture();
        ImageView picture = layout.findViewById(R.id.page_picture);

        // С помощью Picasso
        Picasso
                .get()                      // Используя Context
                .load(pictureURL)           // Загружаем картинку по URL
                .fit()                      // Автоматически определяем размеры ImageView
                .centerCrop()               // Масштабируем картинку
                .into(picture);             // в ImageView

        TextView name = layout.findViewById(R.id.page_text_place);
        TextView description = layout.findViewById(R.id.page_text_description);
        TextView priceOld = layout.findViewById(R.id.page_text_placeold);
        TextView priceNew = layout.findViewById(R.id.page_text_placenew);

        name.setText(place.getmPlace());
        description.setText(place.getmDescription());
        priceOld.setText(place.getmOldPrice());
        priceNew.setText(place.getmNewPrice());

        // Возвращаем "надутый" и настроенный view
        container.addView(layout);

        return layout;
    }

    @Override
    public int getCount() {
        return mPlace == null ? 0 : mPlace.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
