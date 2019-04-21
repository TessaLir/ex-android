package ru.vetukov.spec.java.level01.beautifulplacesstarter;

import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.viewpagerindicator.UnderlinePageIndicator;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;

    private List<Place> mPlaces = new ArrayList<>();
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            toolbar.setTitle("Beautiful Places");
//        }
//
//        setSupportActionBar(toolbar);

        mPager = findViewById(R.id.main_pager);

        mPlaces.add(new Place("Монако", "В Столице суверенного княжества Монако живет больше миллионеров, чем настройщиков роялей", "$1180", "$999.95", "http://media.globalchampionstour.com/cache/750x429/assets/monaco_2016.jpg"));
        mPlaces.add(new Place("Прага", "Культурная столица восточной европы - город, который хорош в любое время года", "$180", "$80", "http://www.pragueczechtravel.com/images/prague_banner.jpg"));
        mPlaces.add(new Place("Таллинн", "Столица прибалтийской жемчужины Эстонии", "$245", "$15", "http://cbpspb.ru/assets/images/bbb/tallinn-1.jpg"));
        mPlaces.add(new Place("Озеро Комо", "Живописное озеро в северной Италии", "$845", "$799", "https://www.travcoa.com/sites/default/files/styles/flexslider_full/public/tours/images/veniceandlakecomo-hero-italy-lake-como-menaggio-41965520.jpg?itok=fROUMZe2"));

        mPagerAdapter = new MyPageAdapter(mPlaces);

        mPager.setAdapter(mPagerAdapter);

        UnderlinePageIndicator underlinePageIndicator = findViewById(R.id.indicator);

        if(underlinePageIndicator != null) {
            underlinePageIndicator.setViewPager(mPager);
        }

    }

    public void fabClicked(View view) {
        Snackbar.make(
                findViewById(R.id.main_coordinator),
                "Путешествие заказано",
                Snackbar.LENGTH_SHORT
        ).show();
    }

}
