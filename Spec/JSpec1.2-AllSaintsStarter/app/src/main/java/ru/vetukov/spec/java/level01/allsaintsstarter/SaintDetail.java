package ru.vetukov.spec.java.level01.allsaintsstarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RatingBar;

public class SaintDetail extends AppCompatActivity {

    private WebView mSaintWebView;
    private RatingBar mSaintRating;
    private int mSaintID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saint_detail);

        mSaintWebView = findViewById(R.id.item_saint_detail);
        mSaintRating = findViewById(R.id.item_rating);

        mSaintWebView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();

        if (intent != null) {
            final String saint = intent.getStringExtra(MainActivity.SAINT_NAME);
            if (saint != null) {
                saint.replace(" ","_");
                String url = "https://en.m.wikipedia.org/wiki/" + saint;
                mSaintWebView.loadUrl(url);
            }

            if(intent.hasExtra(MainActivity.SAINT_RATING))
            {
                mSaintRating.setRating(intent.getFloatExtra(MainActivity.SAINT_RATING, 0f));
            }

            if(intent.hasExtra(MainActivity.SAINT_ID))
            {
                mSaintID = intent.getIntExtra(MainActivity.SAINT_ID, 0);
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();

        intent.putExtra(MainActivity.SAINT_ID, mSaintID);
        intent.putExtra(MainActivity.SAINT_RATING, mSaintRating.getRating());

        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }
}
